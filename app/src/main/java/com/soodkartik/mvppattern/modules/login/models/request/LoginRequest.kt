package com.soodkartik.mvppattern.modules.login.models.request

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("userName") var username: String? = null,
    var password: String? = null,
    var token: String? = null
) : BaseObservable()