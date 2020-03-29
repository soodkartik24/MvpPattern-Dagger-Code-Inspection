package com.soodkartik.mvppattern.base.network

import retrofit2.Response
import retrofit2.http.*

interface API {
    @POST
    suspend fun request(@Url url: String?, @Body pRequest: Any?): Response<Any?>?

    @GET
    suspend fun getRequest(
        @Url url: String?,
        @QueryMap options: @JvmSuppressWildcards Map<String, Any>
    ): Response<Any?>?
}