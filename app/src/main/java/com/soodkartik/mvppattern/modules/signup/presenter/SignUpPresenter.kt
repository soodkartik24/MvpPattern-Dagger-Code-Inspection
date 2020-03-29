package com.soodkartik.mvppattern.modules.signup.presenter

import com.soodkartik.mvppattern.modules.signup.models.request.SignUpRequest

interface SignUpPresenter {
    fun doSignUp(signUpRequest: SignUpRequest)
}