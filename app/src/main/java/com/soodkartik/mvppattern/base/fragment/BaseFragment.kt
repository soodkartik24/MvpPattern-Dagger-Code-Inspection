package com.soodkartik.mvppattern.base.fragment

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.base.activity.BaseActivity
import com.soodkartik.mvppattern.base.presenter.MVPView
import com.soodkartik.mvppattern.constants.Enums
import com.soodkartik.mvppattern.utilities.UserAlertUtility
import com.soodkartik.mvppattern.utilities.UserProgressUtility
import com.soodkartik.mvppattern.utilities.showToast

abstract class BaseFragment : Fragment(), MVPView {
    private var mActivity: BaseActivity? = null

    abstract fun setUp(view: View)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            mActivity = context
        }
        mActivity?.let { TravelAppApplication.initDaggerActivity(it, it) }
    }

    private fun getActivityInstance(): BaseActivity? {
        return mActivity
    }

    override fun onError(pErrorMessage: String?, pType: Enums.ErrorType, pCode: Int?) {
        when (pType) {
            Enums.ErrorType.VALIDATION -> {
                if (getActivityInstance()?.currentFocus != null) {
                    UserAlertUtility.showSnackBar(
                        pErrorMessage,
                        getActivityInstance()?.currentFocus,
                        getActivityInstance()
                    )
                } else {
                    getActivityInstance()?.showToast(pErrorMessage)
                }
            }
            Enums.ErrorType.API, Enums.ErrorType.OTHER -> {
                UserAlertUtility.showAlertDialog(
                    getString(R.string.error_text),
                    pErrorMessage,
                    getActivityInstance(),
                    null,
                    null
                )
            }
        }
    }

    override fun showProgress(pMessage: String, pIsCancelable: Boolean) {
        UserProgressUtility.showProgressDialog(getActivityInstance())
    }

    override fun hideProgress() {
        UserProgressUtility.hideProgressDialog()
    }

    override fun onSuccess(pResponse: Any?, pIsPaginatedCall: Boolean?) {

    }

    override fun onFailure() {

    }
}