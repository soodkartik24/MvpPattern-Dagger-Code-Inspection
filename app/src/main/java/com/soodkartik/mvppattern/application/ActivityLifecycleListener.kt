package com.soodkartik.mvppattern.application

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.soodkartik.mvppattern.utilities.SharedPreferencesUtility
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ActivityLifecycleListener internal constructor() : Application.ActivityLifecycleCallbacks {

    @Inject
    lateinit var preferencesService: SharedPreferencesUtility
    private var backgroundedAt = System.currentTimeMillis()
    private var activityCount = 0

    init {
        TravelAppApplication.getApplicationComponent()?.inject(this)
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity?) {
        activityCount++
    }

    override fun onActivityResumed(activity: Activity?) {

    }

    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityStopped(activity: Activity?) {
        activityCount--
        if (activityCount == 0) {
            backgroundedAt = System.currentTimeMillis()
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

    override fun onActivityDestroyed(activity: Activity?) {}

    companion object {
        private val TIMEOUT_BACKGROUND_SESSION = TimeUnit.MINUTES.toMillis(5)
    }
}
