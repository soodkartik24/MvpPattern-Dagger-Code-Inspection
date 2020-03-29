package com.soodkartik.mvppattern.modules.signup.models.request

import androidx.databinding.BaseObservable
import com.soodkartik.mvppattern.modules.signup.models.UserDetail
import com.google.gson.annotations.SerializedName

data class SignUpRequest constructor(
    @SerializedName("fcmtoken") var fcmToken: String? = null,
    @SerializedName("userdetail") var userDetail: UserDetail? = null,
    var password: String? = null,
    @SerializedName("username") var userName: String? = null,
    @SerializedName("usertype") var userType: String? = null
) : BaseObservable()