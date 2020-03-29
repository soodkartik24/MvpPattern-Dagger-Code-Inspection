package com.soodkartik.mvppattern.modules.signup.presenter

import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.base.presenter.BasePresenterImp
import com.soodkartik.mvppattern.base.presenter.MVPView
import com.soodkartik.mvppattern.constants.Constants
import com.soodkartik.mvppattern.constants.Enums
import com.soodkartik.mvppattern.modules.signup.models.request.SignUpRequest
import com.soodkartik.mvppattern.modules.signup.models.response.SignUpResponse
import com.soodkartik.mvppattern.service.API
import com.soodkartik.mvppattern.utilities.DateFormatterUtility
import com.soodkartik.mvppattern.utilities.ValidationsUtility

class SignUpPresenterImp(private val pMVPView: MVPView) : BasePresenterImp(pMVPView),
    SignUpPresenter {
    private var mMessage: String = Constants.sEmptyString
    private val mApplication =
        TravelAppApplication.getApplicationComponent()?.getApplicationInstance()

    override fun doSignUp(signUpRequest: SignUpRequest) {
        if (isValid(signUpRequest)) {
            val date = DateFormatterUtility.convertLocalTimeToUTC(
                Constants.DateFormat.sUTC_TIME_FORMAT, DateFormatterUtility
                    .getStringAsDate(
                        Constants.DateFormat.sDATE_SLASH_FORMAT,
                        signUpRequest.userDetail?.dateOfBirthLocal
                    )
            )
            signUpRequest.userDetail?.dateOfBirth = date
            doRequest(
                API.sSignUp,
                signUpRequest,
                SignUpResponse::class.java,
                Enums.RequestType.Post,
                true
            )
        } else {
            pMVPView.onError(
                mMessage,
                Enums.ErrorType.VALIDATION,
                Constants.ResponseCodes.sDEFAULT_CODE
            )
        }
    }

    private fun isValid(pSignUpRequest: SignUpRequest): Boolean {
        when {
            pSignUpRequest.userDetail?.email.isNullOrEmpty() ||
                    !ValidationsUtility.isValidEmailAddress(
                        pSignUpRequest.userDetail?.email
                    ) -> {
                mMessage = mApplication?.getString(R.string.enter_valid_email).toString()
                return false
            }
            pSignUpRequest.userName.isNullOrEmpty() -> {
                mMessage = mApplication?.getString(R.string.enter_valid_username).toString()
                return false
            }
            pSignUpRequest.password.isNullOrEmpty() -> {
                mMessage = mApplication?.getString(R.string.empty_password_msg).toString()
                return false
            }
            pSignUpRequest.userDetail?.customerName.isNullOrEmpty() -> {
                mMessage = mApplication?.getString(R.string.enter_customer_name).toString()
                return false
            }
            pSignUpRequest.userDetail?.dateOfBirthLocal.isNullOrEmpty() -> {
                mMessage = mApplication?.getString(R.string.enter_valid_dob).toString()
                return false
            }
            pSignUpRequest.userDetail?.mobileNo.isNullOrEmpty() ||
                    (!TravelAppApplication.getApplicationComponent()?.getPhoneUtilityInstance()
                        ?.isValidNumber(pSignUpRequest.userDetail?.mobileNo)!!) -> {
                mMessage = mApplication?.getString(R.string.enter_valid_mbno).toString()
                return false
            }
            pSignUpRequest.userDetail?.gender == Enums.Gender.None.type -> {
                mMessage = mApplication?.getString(R.string.select_gender).toString()
                return false
            }
            pSignUpRequest.userDetail?.country.isNullOrEmpty() -> {
                mMessage = mApplication?.getString(R.string.enter_country).toString()
                return false
            }
            pSignUpRequest.userDetail?.state.isNullOrEmpty() -> {
                mMessage = mApplication?.getString(R.string.enter_valid_state).toString()
                return false
            }
            pSignUpRequest.userDetail?.city.isNullOrEmpty() -> {
                mMessage = mApplication?.getString(R.string.enter_city).toString()
                return false
            }
            pSignUpRequest.userDetail?.pincode.isNullOrEmpty() -> {
                mMessage = mApplication?.getString(R.string.enter_valid_pincode).toString()
                return false
            }
            pSignUpRequest.userDetail?.adhaarCardNumber.isNullOrEmpty() -> {
                mMessage = mApplication?.getString(R.string.empty_adhaarcard__msg).toString()
                return false
            }
            else -> return true
        }
    }
}