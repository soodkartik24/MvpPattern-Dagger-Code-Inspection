package com.soodkartik.mvppattern.modules.otp.activity

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.base.activity.BaseActivity
import com.soodkartik.mvppattern.databinding.ActivityOtpBinding
import com.soodkartik.mvppattern.modules.otp.presenter.OtpPresenter
import com.soodkartik.mvppattern.utilities.ActivityUtility.openIntent
import javax.inject.Inject

class OtpActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            context.openIntent(OtpActivity::class, null)
        }
    }

    private var mOtpActivityBinding: ActivityOtpBinding? = null

    @Inject
    lateinit var otpPresenter: OtpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TravelAppApplication.getActivityComponent()?.inject(this)
        mOtpActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_otp)
    }
}
