package com.soodkartik.mvppattern.base.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.base.presenter.BasePresenter
import com.soodkartik.mvppattern.base.presenter.MVPView
import com.soodkartik.mvppattern.constants.Constants
import com.soodkartik.mvppattern.constants.Enums
import com.soodkartik.mvppattern.databinding.ActivityBaseBinding
import com.soodkartik.mvppattern.utilities.*
import javax.inject.Inject


open class BaseActivity : AppCompatActivity(), MVPView {
    private var mActivityBaseBinding: ActivityBaseBinding? = null
    private var mView: View? = null

    @Inject
    lateinit var sharedPreferencesUtility: SharedPreferencesUtility


    override fun onCreate(savedInstanceState: Bundle?) {
        initActivityToApplication()
        super.onCreate(savedInstanceState)
        mActivityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        mView = if (currentFocus == null) {
            mActivityBaseBinding?.parentView?.findFocus()
        } else {
            currentFocus
        }
    }

    private fun initActivityToApplication() {
        TravelAppApplication.initDaggerActivity(this, this)
    }

    override fun onError(pErrorMessage: String?, pType: Enums.ErrorType, pCode: Int?) {
        when (pType) {
            Enums.ErrorType.VALIDATION -> {
                if (mView != null) {
                    UserAlertUtility.showSnackBar(pErrorMessage, mView, this)
                } else {
                    if (currentFocus != null) {
                        mView = currentFocus
                        UserAlertUtility.showSnackBar(pErrorMessage, mView, this)
                    } else {
                        showToast(pErrorMessage)
                    }
                }
            }
            Enums.ErrorType.API, Enums.ErrorType.OTHER -> {
                UserAlertUtility.showAlertDialog(
                    getString(R.string.error_text),
                    pErrorMessage,
                    TravelAppApplication.getActivityComponent()?.getActivity(),
                    null,
                    null
                )
            }
        }
    }

    override fun showProgress(pMessage: String, pIsCancelable: Boolean) {
        UserProgressUtility.showProgressDialog(this)
    }

    override fun hideProgress() {
        UserProgressUtility.hideProgressDialog()
    }

    override fun onSuccess(pResponse: Any?, pIsPaginatedCall: Boolean?) {

    }

    override fun onFailure() {

    }

    override fun finishView() {
        finish()
    }

    fun getFcmToken(): String? {
        return sharedPreferencesUtility.getData(
            Enums.SharePreferencesEnum.StringType.name,
            Constants.SharedPreferences.sFcmToken
        ) as? String?
    }


    fun cancelJob(basePresenter: BasePresenter) {
        basePresenter.doCancel()
    }
}