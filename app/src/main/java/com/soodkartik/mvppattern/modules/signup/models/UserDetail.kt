package com.soodkartik.mvppattern.modules.signup.models

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("adharcard") var adhaarCardNumber: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("customername") var customerName: String? = null,
    @SerializedName("dateofbirth") var dateOfBirth: String? = null,
    var dateOfBirthLocal: String? = null, //this is local field
    @SerializedName("email") var email: String? = null,
    @SerializedName("gender") var gender: Int? = null,
    @SerializedName("mobileno") var mobileNo: String? = null,
    @SerializedName("pincode") var pincode: String? = null,
    @SerializedName("state") var state: String? = null
) : BaseObservable()