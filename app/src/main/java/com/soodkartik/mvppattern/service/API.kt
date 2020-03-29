package com.soodkartik.mvppattern.service

import com.soodkartik.mvppattern.constants.Constants

object API {
    object QueryParams {
        const val sUsername = "username"
        const val sPassword = "password"
        const val sToken = "token"
    }

    private const val sUser = "User"
    const val sLogin = Constants.sCOLON.plus(URLs.Retrofit.sLOGIN_PORT).plus("/") + "login"
    const val sSignUp =
        Constants.sCOLON.plus(URLs.Retrofit.sSIGNUP_PORT).plus("/") + sUser.plus("/").plus("SignUp")
}