package com.soodkartik.mvppattern.dagger.presenter

import com.soodkartik.mvppattern.base.presenter.MVPView
import com.soodkartik.mvppattern.dagger.activity.ActivityScope
import com.soodkartik.mvppattern.modules.dashboard.presenter.DashboardPresenter
import com.soodkartik.mvppattern.modules.dashboard.presenter.DashboardPresenterImp
import com.soodkartik.mvppattern.modules.launcher.presenter.SplashPresenter
import com.soodkartik.mvppattern.modules.launcher.presenter.SplashPresenterImp
import com.soodkartik.mvppattern.modules.login.presenter.LoginPresenter
import com.soodkartik.mvppattern.modules.login.presenter.LoginPresenterImp
import com.soodkartik.mvppattern.modules.otp.presenter.OtpPresenter
import com.soodkartik.mvppattern.modules.otp.presenter.OtpPresenterImp
import com.soodkartik.mvppattern.modules.signup.presenter.SignUpPresenter
import com.soodkartik.mvppattern.modules.signup.presenter.SignUpPresenterImp
import dagger.Module
import dagger.Provides

@Module
class PresenterModule(private val pMVPView: MVPView) {

    @Provides
    @ActivityScope
    fun provideSplashPresenter(): SplashPresenter = SplashPresenterImp(pMVPView)

    @Provides
    @ActivityScope
    fun provideLoginPresenter(): LoginPresenter = LoginPresenterImp(pMVPView)

    @Provides
    @ActivityScope
    fun provideSignUpnPresenter(): SignUpPresenter = SignUpPresenterImp(pMVPView)

    @Provides
    @ActivityScope
    fun provideOtpPresenter(): OtpPresenter = OtpPresenterImp(pMVPView)

    @Provides
    @ActivityScope
    fun provideDashBoardPresenter(): DashboardPresenter = DashboardPresenterImp(pMVPView)
}