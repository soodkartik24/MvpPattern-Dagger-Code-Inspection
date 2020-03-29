package com.soodkartik.mvppattern.dagger.application

import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.service.BaseService
import com.soodkartik.mvppattern.utilities.PhoneNumberUtility
import com.soodkartik.mvppattern.utilities.SharedPreferencesUtility
import dagger.Module
import dagger.Provides
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: TravelAppApplication?) {

    @Provides
    @ApplicationScope
    fun providesApplication(): TravelAppApplication? {
        return application
    }

    @Provides
    @Singleton
    fun providesPhoneUtilInstance(): PhoneNumberUtil {
        return PhoneNumberUtil.createInstance(application)
    }

    @Provides
    @ApplicationScope
    fun providesPhoneNumberUtility(): PhoneNumberUtility {
        return PhoneNumberUtility(providesPhoneUtilInstance())
    }

    @Provides
    @ApplicationScope
    fun providePreferencesService(): SharedPreferencesUtility {
        return SharedPreferencesUtility(application)
    }

    @Provides
    @ApplicationScope
    fun provideBaseService(): BaseService {
        return BaseService(application)
    }

}