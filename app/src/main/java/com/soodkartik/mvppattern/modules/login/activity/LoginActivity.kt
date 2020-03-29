package com.soodkartik.mvppattern.modules.login.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.base.activity.BaseActivity
import com.soodkartik.mvppattern.constants.Constants
import com.soodkartik.mvppattern.constants.Enums
import com.soodkartik.mvppattern.databinding.ActivityLoginBinding
import com.soodkartik.mvppattern.modules.dashboard.activity.DashboardActivity
import com.soodkartik.mvppattern.modules.login.models.request.LoginRequest
import com.soodkartik.mvppattern.modules.login.models.response.LoginResponse
import com.soodkartik.mvppattern.modules.login.presenter.LoginPresenter
import com.soodkartik.mvppattern.modules.login.presenter.LoginPresenterImp
import com.soodkartik.mvppattern.modules.signup.activity.SignUpActivity
import com.soodkartik.mvppattern.utilities.ActivityUtility.openIntent
import javax.inject.Inject

class LoginActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.openIntent(LoginActivity::class)
        }
    }

    @Inject
    lateinit var mLoginPresenter: LoginPresenter

    private var mActivityLoginBinding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TravelAppApplication.getActivityComponent()?.inject(this)
        mActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mActivityLoginBinding?.activity = this
        initLoginRequestMode()
    }

    private fun initLoginRequestMode() {
        mActivityLoginBinding?.loginRequest = LoginRequest()
    }

    fun onSubmitButtonClick(@Suppress("UNUSED_PARAMETER") view: View, loginRequest: LoginRequest) {
        loginRequest.token = getFcmToken()
        mLoginPresenter.doLogin(loginRequest)
    }

    fun onSignUpButtonCLick(@Suppress("UNUSED_PARAMETER") view: View) {
        openSignUpActivity()
    }

    override fun onSuccess(pResponse: Any?, pIsPaginatedCall: Boolean?) {
        when (pResponse) {
            is LoginResponse -> handleLoginResponse(loginResponse = pResponse)
        }
    }

    private fun handleLoginResponse(@Suppress("UNUSED_PARAMETER") loginResponse: LoginResponse) {
        openDashboardActivity()
        setLoginStatus()
    }

    private fun openSignUpActivity() {
        SignUpActivity.start(this)
    }

    private fun openDashboardActivity() {
        DashboardActivity.start(this)
    }

    private fun setLoginStatus() {
        sharedPreferencesUtility.setData(
            Enums.SharePreferencesEnum.BooleanType.name,
            Constants.SharedPreferences.sIsLogIn,
            true
        )
    }

    override fun onDestroy() {
        (mLoginPresenter as? LoginPresenterImp?).let {
            if (it != null) {
                cancelJob(it)
            }
        }
        super.onDestroy()
    }
}
