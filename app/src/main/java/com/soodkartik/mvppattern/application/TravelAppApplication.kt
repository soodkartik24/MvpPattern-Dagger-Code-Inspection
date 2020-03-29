package com.soodkartik.mvppattern.application

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.soodkartik.mvppattern.base.presenter.MVPView
import com.soodkartik.mvppattern.dagger.activity.ActivityComponent
import com.soodkartik.mvppattern.dagger.activity.ActivityModule
import com.soodkartik.mvppattern.dagger.activity.DaggerActivityComponent
import com.soodkartik.mvppattern.dagger.application.ApplicationComponent
import com.soodkartik.mvppattern.dagger.application.ApplicationModule
import com.soodkartik.mvppattern.dagger.application.DaggerApplicationComponent
import com.soodkartik.mvppattern.dagger.presenter.PresenterModule

class TravelAppApplication : Application() {
    companion object {
        private var applicationComponent: ApplicationComponent? = null

        fun getApplicationComponent(): ApplicationComponent? {
            return applicationComponent
        }

        private fun setApplicationComponent(applicationComponent: ApplicationComponent) {
            this.applicationComponent = applicationComponent
        }

        private var activityComponent: ActivityComponent? = null

        fun getActivityComponent(): ActivityComponent? {
            return activityComponent
        }

        private fun setActivityComponent(activityComponent: ActivityComponent) {
            this.activityComponent = activityComponent
        }

        fun initDaggerActivity(pActivity: AppCompatActivity, pMVPView: MVPView) {
            setActivityComponent(
                DaggerActivityComponent.builder()
                    .applicationComponent(applicationComponent)
                    .activityModule(ActivityModule(pActivity))
                    .presenterModule(PresenterModule(pMVPView))
                    .build()
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerApplication()
        registerActivityLifecycleCallbacks(ActivityLifecycleListener())
    }

    private fun initDaggerApplication() {
        setApplicationComponent(
            DaggerApplicationComponent.builder()
                .applicationModule(
                    ApplicationModule(this)
                ).build()
        )
    }


}