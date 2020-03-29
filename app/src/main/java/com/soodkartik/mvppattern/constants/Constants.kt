package com.soodkartik.mvppattern.constants

object Constants {
    const val sEmptyString = ""
    const val sCOLON = ":"
    const val sSNACKBAR_MAX_LINES = 3
    const val TAG = "Travel App"
    const val sSplashScreenTimeOut = 3000 //3 sec

    object Logger {
        const val sUrlRequest = "UrlRequest:"
        const val sNewToken = "NewToken:"
    }

    object Pattern {
        const val sEmailRegularExpression =
            "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+" + "(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,10}))?$"
    }

    object ResponseCodes {
        const val sSUCCESS = 200
        const val sDATA = "data"
        const val sINTERNAL_SERVER_ERROR = 500
        const val sUNAUTHORIZED_ACCESS = 401
        const val sSOMETHING_WRONG = 10001
        const val sDEFAULT_CODE = -1
        const val sAPI_STATUS_SUCCESS = 1
    }

    object Notification {
        const val sNotificationId = 10001
    }

    object SharedPreferences {
        const val sDEFAULT_INT_VALUE = 0
        const val sDEFAULT_LONG_VALUE = 0
        const val sDEFAULT_FLOAT_VALUE = 0.0f
        const val sDEFAULT_BOOLEAN_VALUE = false
        const val sSHARED_PREFERENCES = "defaultSharedPreferences"
        const val sFcmToken = "fcmToken"
        const val sIsLogIn = "logIn"
    }

    object DateFormat {
        const val sUTC_FORMAT = "UTC"
        const val sUTC_TIME_FORMAT = "yyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val sDATE_TIME_FORMAT = "MM/dd/yyyy hh:mm a"
        const val sDATE_TIME_SHORT_YY_FORMAT = "MM/dd/yy, hh:mm a"
        const val sDATE_HOUR_TF_FORMAT = "MM/dd/yyyy HH"
        const val sTIME_FORMAT = "hh:mm a"
        const val sDATE_SLASH_FORMAT = "MM/dd/yyyy"
        const val sDATE_SLASH_SHORT_YY_FORMAT = "MM/dd/yy"
        const val sDATE_DAY_FORMAT = "EEE, dd MMM, hh:mm a"
        const val sYEAR = "yyyy"
    }

}