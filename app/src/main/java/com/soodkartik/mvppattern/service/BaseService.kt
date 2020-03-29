package com.soodkartik.mvppattern.service

import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.base.network.API
import com.soodkartik.mvppattern.constants.Constants
import com.soodkartik.mvppattern.utilities.LoggerUtility
import com.soodkartik.mvppattern.utilities.NetworkUtility
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BaseService @Inject internal constructor(pApplication: TravelAppApplication?) {
    private var mAuthorization: String = Constants.sEmptyString

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(URLs.Retrofit.sTime.toLong(), TimeUnit.SECONDS)
        .readTimeout(URLs.Retrofit.sTime.toLong(), TimeUnit.SECONDS)
        .writeTimeout(URLs.Retrofit.sTime.toLong(), TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder =
                original.newBuilder()
                    .header(URLs.Retrofit.sAuthorizationHeader, mAuthorization)
                    .addHeader(URLs.Retrofit.sContentType, URLs.Retrofit.sContentTypeJson)
                    .method(original.method(), original.body())
            val request = requestBuilder.build()
            LoggerUtility.d(Constants.Logger.sUrlRequest, request.url().toString())
            chain.proceed(request)
        }
        .addInterceptor(
            NetworkUtility(
                pApplication?.applicationContext
            )
        )


    private fun createOkHttpClient(): OkHttpClient = okHttpClient.build()

    fun getClient(): API {
        val baseUrl: String = URLs.Retrofit.sAPI_BASE_URL

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()
            .create(API::class.java)
    }


    fun setAuthorization(pAuthorization: String) {
        mAuthorization = pAuthorization
    }
}