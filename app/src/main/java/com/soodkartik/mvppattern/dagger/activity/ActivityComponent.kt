package com.soodkartik.mvppattern.dagger.activity

import androidx.appcompat.app.AppCompatActivity
import com.soodkartik.mvppattern.dagger.application.ApplicationComponent
import com.soodkartik.mvppattern.dagger.presenter.PresenterModule
import com.soodkartik.mvppattern.modules.launcher.activity.SplashActivity
import com.soodkartik.mvppattern.modules.login.activity.LoginActivity
import com.soodkartik.mvppattern.modules.otp.activity.OtpActivity
import com.soodkartik.mvppattern.modules.signup.activity.SignUpActivity
import dagger.Component


@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class, PresenterModule::class]
)
@ActivityScope
interface ActivityComponent {
    fun getActivity(): AppCompatActivity?

    fun inject(splashActivity: SplashActivity?)
    fun inject(loginActivity: LoginActivity?)
    fun inject(signUpActivity: SignUpActivity?)
    fun inject(otpActivity: OtpActivity?)

}