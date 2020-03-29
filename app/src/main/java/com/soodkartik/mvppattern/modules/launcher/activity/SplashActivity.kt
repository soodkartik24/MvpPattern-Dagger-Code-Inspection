package com.soodkartik.mvppattern.modules.launcher.activity

import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.base.activity.BaseActivity
import com.soodkartik.mvppattern.constants.Constants
import com.soodkartik.mvppattern.databinding.ActivitySplashBinding
import com.soodkartik.mvppattern.modules.launcher.presenter.SplashPresenter
import com.soodkartik.mvppattern.modules.login.activity.LoginActivity
import com.soodkartik.mvppattern.utilities.ActivityUtility.openIntent
import javax.inject.Inject

class SplashActivity : BaseActivity() {
    private var mSplashBinding: ActivitySplashBinding? = null

    @Inject
    lateinit var mSplashPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TravelAppApplication.getActivityComponent()?.inject(this)
        mSplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler().postDelayed({
            mSplashPresenter.onViewCreated()
        }, Constants.sSplashScreenTimeOut.toLong())
    }

    override fun finishView() {
        openIntent(LoginActivity::class)
        mSplashPresenter.onViewDestroyed()
        super.finishView()
    }
}