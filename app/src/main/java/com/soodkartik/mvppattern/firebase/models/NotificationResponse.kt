package com.soodkartik.mvppattern.firebase.models

import com.soodkartik.mvppattern.base.models.response.BaseResponse

data class NotificationResponse(
    val title: String? = null,
    val body: String? = null
) : BaseResponse()