package com.soodkartik.mvppattern.modules.login.presenter

import com.soodkartik.mvppattern.modules.login.models.request.LoginRequest

interface LoginPresenter {
    fun doLogin(pLoginRequest: LoginRequest?)
}