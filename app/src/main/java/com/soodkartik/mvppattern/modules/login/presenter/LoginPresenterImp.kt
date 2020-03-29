package com.soodkartik.mvppattern.modules.login.presenter

import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.base.presenter.BasePresenterImp
import com.soodkartik.mvppattern.base.presenter.MVPView
import com.soodkartik.mvppattern.constants.Constants
import com.soodkartik.mvppattern.constants.Enums
import com.soodkartik.mvppattern.modules.login.models.request.LoginRequest
import com.soodkartik.mvppattern.modules.login.models.response.LoginResponse
import com.soodkartik.mvppattern.service.API
import com.soodkartik.mvppattern.utilities.StringEncryptionUtility
import javax.inject.Inject

class LoginPresenterImp
@Inject internal constructor(private val pMVPView: MVPView) : BasePresenterImp(pMVPView),
    LoginPresenter {

    private var mErrorMessage: String? = Constants.sEmptyString
    override fun doLogin(pLoginRequest: LoginRequest?) {
        if (isCredentialsValid(pLoginRequest)) {
            val encryptionPassword = StringEncryptionUtility.getData(pLoginRequest?.password)
            val map = HashMap<String, Any?>()
            map[API.QueryParams.sUsername] = pLoginRequest?.username
            map[API.QueryParams.sPassword] = encryptionPassword
            map[API.QueryParams.sToken] = pLoginRequest?.token
            doRequest(
                API.sLogin,
                map,
                LoginResponse::class.java,
                Enums.RequestType.Post,
                true
            )
        } else {
            pMVPView.onError(
                mErrorMessage,
                Enums.ErrorType.VALIDATION,
                Constants.ResponseCodes.sDEFAULT_CODE
            )
        }
    }

    fun isCredentialsValid(pLoginRequest: LoginRequest?): Boolean {
        return when {
            pLoginRequest?.username.isNullOrEmpty() -> {
                mErrorMessage =
                    TravelAppApplication.getApplicationComponent()?.getApplicationInstance()
                        ?.getString(R.string.enter_valid_username)
                false
            }
            pLoginRequest?.password.isNullOrEmpty() -> {
                mErrorMessage =
                    TravelAppApplication.getApplicationComponent()?.getApplicationInstance()
                        ?.getString(R.string.enter_valid_password)
                false
            }
            else -> true
        }
    }
}