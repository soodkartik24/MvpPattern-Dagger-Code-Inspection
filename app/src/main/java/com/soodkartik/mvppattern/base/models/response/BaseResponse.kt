package com.soodkartik.mvppattern.base.models.response

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("msg") val message: String? = null,
    @SerializedName("status") val status: Boolean? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("apistatus") val apiStatus: Int = -1,
    @SerializedName("statusCode") val statusCode: Int = -1,
    @SerializedName("apimsg") val apiMsg: String? = null,
    @SerializedName("responseStatusMsg") val responseStatusMsg: String? = null
)
