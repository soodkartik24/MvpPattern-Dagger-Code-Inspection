package com.soodkartik.mvppattern.modules.signup.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.base.activity.BaseActivity
import com.soodkartik.mvppattern.constants.Constants
import com.soodkartik.mvppattern.databinding.ActivitySignUpBinding
import com.soodkartik.mvppattern.interfaces.BackButtonListener
import com.soodkartik.mvppattern.modules.signup.models.UserDetail
import com.soodkartik.mvppattern.modules.signup.models.request.SignUpRequest
import com.soodkartik.mvppattern.modules.signup.models.response.SignUpResponse
import com.soodkartik.mvppattern.modules.signup.presenter.SignUpPresenter
import com.soodkartik.mvppattern.modules.signup.presenter.SignUpPresenterImp
import com.soodkartik.mvppattern.utilities.*
import com.soodkartik.mvppattern.utilities.ActivityUtility.openIntent
import com.soodkartik.mvppattern.utilities.interfaces.DatePickerInterface
import javax.inject.Inject

class SignUpActivity : BaseActivity(), BackButtonListener, DatePickerInterface {

    companion object {
        fun start(context: Context) {
            context.openIntent(SignUpActivity::class)
        }
    }

    private var mSignUpBinding: ActivitySignUpBinding? = null

    @Inject
    lateinit var mSignUpPresenter: SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TravelAppApplication.getActivityComponent()?.inject(this)
        mSignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        mSignUpBinding?.activity = this
        mSignUpBinding?.toolbarLayout?.backinterfacelistener = this
        mSignUpBinding?.toolbarLayout?.toolbarText?.text = getString(R.string.signuo_text)
        mSignUpBinding?.signupmodel = SignUpRequest()
        mSignUpBinding?.userDetailModel = UserDetail()
        mSignUpBinding?.spinnerBinding = SpinnerBindingUtility

        initSpinnerLayout()
    }

    private fun initSpinnerLayout() {
        SpinnerBindingUtility.setSpinnerLayout(
            this,
            R.array.gender_array,
            mSignUpBinding?.genderSpinner
        )
    }

    override fun onBackButtonClicked(view: View) {
        finishView()
    }

    fun onSubmitButtonClick(
        @Suppress("UNUSED_PARAMETER") view: View,
        signUpRequest: SignUpRequest,
        userDetail: UserDetail
    ) {
        signUpRequest.fcmToken = getFcmToken()
        userDetail.gender = mSignUpBinding?.genderSpinner?.selectedItemPosition
        signUpRequest.userDetail = userDetail
        mSignUpPresenter.doSignUp(signUpRequest)
    }

    fun onDateOfBirthViewClick(@Suppress("UNUSED_PARAMETER") view: View) {
        DatePickerUtility.showDatePicker(
            this,
            this,
            DateFormatterUtility.getStringAsDate(
                Constants.DateFormat.sDATE_SLASH_FORMAT,
                mSignUpBinding?.userDetailModel?.dateOfBirth
            )
        )
    }

    override fun onDateGet(pDayOfMonth: Int, pYear: Int, pMonth: Int) {
        val date = DateFormatterUtility.getDate(pDayOfMonth, pYear, pMonth - 1)
        mSignUpBinding?.userDetailModel?.dateOfBirthLocal =
            DateFormatterUtility.getDateAsString(Constants.DateFormat.sDATE_SLASH_FORMAT, date)
        mSignUpBinding?.userDetailModel?.notifyChange()
    }

    override fun onSuccess(pResponse: Any?, pIsPaginatedCall: Boolean?) {
        when (pResponse) {
            is SignUpResponse -> handleSignUpResponse(pResponse)
        }
    }

    private fun handleSignUpResponse(pSignUpResponse: SignUpResponse) {
        if (pSignUpResponse.statusCode == Constants.ResponseCodes.sAPI_STATUS_SUCCESS) {
            showToast(pSignUpResponse.responseStatusMsg)
            finishView()
        } else {
            UserAlertUtility.showAlertDialog(
                getString(R.string.error_text),
                pSignUpResponse.responseStatusMsg,
                this
            )
        }
    }

    override fun onDestroy() {
        (mSignUpPresenter as? SignUpPresenterImp?).let {
            if (it != null) {
                cancelJob(it)
            }
        }
        super.onDestroy()
    }
}