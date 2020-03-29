package com.soodkartik.mvppattern.firebase.pushnotifications

import android.content.Intent
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.constants.Constants
import com.soodkartik.mvppattern.constants.Enums
import com.soodkartik.mvppattern.firebase.models.NotificationResponse
import com.soodkartik.mvppattern.utilities.LoggerUtility
import com.soodkartik.mvppattern.utilities.NotificationUtility
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson

class MyFirebaseMessagingService : FirebaseMessagingService() {


    override fun onNewToken(pToken: String) {
        pToken.let { super.onNewToken(it) }
        LoggerUtility.d(
            Constants.TAG, "${Constants.Logger.sNewToken} $pToken"
        )
        TravelAppApplication.getApplicationComponent()?.providePreferencesService()?.setData(
            Enums.SharePreferencesEnum.StringType.name,
            Constants.SharedPreferences.sFcmToken,
            pToken
        )
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.let { super.onMessageReceived(it) }
        val data = Gson().toJson(remoteMessage.data)
        val notificationResponse = Gson().fromJson(data, NotificationResponse::class.java)
        openNotification(notificationResponse)
    }

    private fun openNotification(notificationResponse: NotificationResponse?) {
        NotificationUtility.displayNotification(
            applicationContext,
            Constants.Notification.sNotificationId,
            notificationResponse?.body,
            notificationResponse?.title ?: Constants.sEmptyString,
            Intent()
        )
    }
}