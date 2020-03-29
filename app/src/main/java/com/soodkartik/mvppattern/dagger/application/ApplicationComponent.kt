package com.soodkartik.mvppattern.dagger.application

import com.soodkartik.mvppattern.application.ActivityLifecycleListener
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.service.BaseService
import com.soodkartik.mvppattern.utilities.PhoneNumberUtility
import com.soodkartik.mvppattern.utilities.SharedPreferencesUtility
import dagger.Component


@Component(modules = [ApplicationModule::class])
@ApplicationScope
interface ApplicationComponent {
    fun providePreferencesService(): SharedPreferencesUtility
    fun provideBaseService(): BaseService
    fun getApplicationInstance(): TravelAppApplication?
    fun getPhoneUtilityInstance(): PhoneNumberUtility?
    fun inject(activityLifecycleListener: ActivityLifecycleListener?)
}