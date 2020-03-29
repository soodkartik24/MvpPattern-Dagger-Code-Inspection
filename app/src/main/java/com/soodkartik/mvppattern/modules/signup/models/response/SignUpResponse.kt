package com.soodkartik.mvppattern.modules.signup.models.response

import com.soodkartik.mvppattern.base.models.response.BaseResponse
import com.soodkartik.mvppattern.modules.signup.models.UserDetail
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class SignUpResponse(
    @SerializedName("data") val signUpDataResponse: SignUpDataResponse? = null
) : BaseResponse()

class SignUpDataResponse(
    @SerializedName("loginid") val loginId: BigDecimal,
    @SerializedName("username") val username: String? = null,
    @SerializedName("fcmtoken") val fcmToken: String? = null,
    @SerializedName("bearertoken") val bearerToken: String? = null,
    @SerializedName("userdetail") val userDetailResponse: UserDetail
)