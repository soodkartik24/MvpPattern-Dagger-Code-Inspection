package com.soodkartik.mvppattern.base.presenter

import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.base.models.response.ErrorResponse
import com.soodkartik.mvppattern.constants.Constants
import com.soodkartik.mvppattern.constants.Enums
import com.soodkartik.mvppattern.utilities.CommonUtility.hideKeyboard
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap
import kotlin.coroutines.CoroutineContext

@Suppress("UNCHECKED_CAST")
open class BasePresenterImp(private val pMVPView: MVPView) : BasePresenter, CoroutineScope {

    private var job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    protected fun doRequest(
        pUrl: String,
        pRequestParams: Any,
        tClass: Class<*>,
        requestType: Enums.RequestType,
        showProgress: Boolean = true,
        progressLabel: String = Constants.sEmptyString,
        pProgressCancelable: Boolean = false,
        pIsPaginatedCall: Boolean = false
    ) {
        val activity = TravelAppApplication.getActivityComponent()?.getActivity()
        activity?.hideKeyboard(activity.currentFocus)
        if (showProgress) {
            pMVPView.showProgress(progressLabel, pProgressCancelable)
        }
        val requestParams = if (pRequestParams is Map<*, *>) {
            pRequestParams
        } else {
            HashMap<String, Any>()
        }
        launch {
            try {
                val api = TravelAppApplication.getApplicationComponent()?.provideBaseService()
                    ?.getClient()
                val response: Response<Any?>? = when (requestType) {
                    Enums.RequestType.Post -> api?.request(pUrl, pRequestParams)
                    Enums.RequestType.Get -> {
                        api?.getRequest(pUrl, requestParams as Map<String, Any>)
                    }
                    else -> return@launch
                }
                withContext(Dispatchers.Main) {
                    handleResponse(response, tClass, pIsPaginatedCall)
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    handleErrorException(
                        exception.cause?.message ?: exception.message
                    )
                }
            }
        }
    }

    private fun handleResponse(
        response: Response<Any?>?,
        tClass: Class<*>,
        pIsPaginatedCall: Boolean
    ) {
        pMVPView.hideProgress()
        if (response?.body() != null) {
            if (response.body() is ArrayList<*>) {
                val jsonString = Gson().toJson(response.body())
                val jsonObject = JsonObject()
                jsonObject.add(
                    Constants.ResponseCodes.sDATA, Gson().fromJson(
                        jsonString,
                        JsonArray::class.java
                    )
                )
                pMVPView.onSuccess(Gson().fromJson(jsonObject, tClass), pIsPaginatedCall)
            } else if (response.body() is LinkedTreeMap<*, *>) {
                try {
                    pMVPView.onSuccess(
                        Gson().fromJson(
                            response.body().toString(),
                            tClass
                        ),
                        pIsPaginatedCall
                    )
                } catch (e: Exception) {
                    try {
                        pMVPView.onSuccess(
                            Gson().fromJson(
                                Gson().toJson(response.body()),
                                tClass
                            ),
                            pIsPaginatedCall
                        )
                    } catch (e1: Exception) {
                        if (e1 is HttpException) {
                            pMVPView.onError(
                                e1.message,
                                Enums.ErrorType.API,
                                e1.code()
                            )
                        } else {
                            pMVPView.onError(
                                e1.message,
                                Enums.ErrorType.API,
                                Constants.ResponseCodes.sSOMETHING_WRONG
                            )
                        }
                    }
                }
            }
        } else {
            pMVPView.hideProgress()
            var isCatchException = true
            var errorMessage = Constants.sEmptyString
            if (response?.errorBody() != null) {
                try {
                    errorMessage = response.errorBody()!!.string()
                    val data = Gson().fromJson(
                        errorMessage,
                        ErrorResponse::class.java
                    )
                    if (data != null && data.message?.isNotEmpty()!!) {
                        pMVPView.onError(
                            data.message,
                            Enums.ErrorType.API,
                            response.code()
                        )
                        isCatchException = false
                    }
                } catch (e: Exception) {
                }
            }
            if (isCatchException) {
                val message: String = when (response?.code()) {
                    Constants.ResponseCodes.sINTERNAL_SERVER_ERROR ->
                        TravelAppApplication.getApplicationComponent()?.getApplicationInstance()
                            ?.getString(
                                R.string.internal_server_error
                            ).toString()
                    Constants.ResponseCodes.sUNAUTHORIZED_ACCESS ->
                        TravelAppApplication.getApplicationComponent()?.getApplicationInstance()
                            ?.getString(
                                R.string.unAuthorized_access
                            ).toString()
                    else -> {
                        if (response?.message()?.isEmpty()!!) errorMessage else response.message()
                    }
                }
                if (response.code() == Constants.ResponseCodes.sUNAUTHORIZED_ACCESS) {
                    pMVPView.onFailure()
                } else {
                    pMVPView.onError(message, Enums.ErrorType.API, response.code())
                }
            }
        }
    }

    private fun handleErrorException(
        pMessage: String?
    ) {
        pMVPView.hideProgress()
        pMVPView.onError(pMessage, Enums.ErrorType.OTHER, Constants.ResponseCodes.sSOMETHING_WRONG)
    }

    override fun doCancel() {
        if (!job.isCompleted) {
            job.cancel()
        }
    }
}