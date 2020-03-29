package com.soodkartik.mvppattern.modules.dashboard.activity

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.base.activity.BaseActivity
import com.soodkartik.mvppattern.databinding.ActivityDashboardBinding
import com.soodkartik.mvppattern.modules.dashboard.presenter.DashboardPresenter
import com.soodkartik.mvppattern.utilities.ActivityUtility.openIntent
import javax.inject.Inject

class DashboardActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.openIntent(DashboardActivity::class, null)
        }
    }

    private var mActivityDashBoardBinding: ActivityDashboardBinding? = null

    @Inject
    lateinit var dashboardPresenter: DashboardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityDashBoardBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
    }
}
