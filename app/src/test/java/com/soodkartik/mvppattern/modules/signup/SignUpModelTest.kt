package com.soodkartik.mvppattern.modules.signup

import com.soodkartik.mvppattern.base.presenter.MVPView
import com.soodkartik.mvppattern.modules.signup.models.request.SignUpRequest
import com.soodkartik.mvppattern.modules.signup.presenter.SignUpPresenterImp
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SignUpModelTest {

    private lateinit var signUpRequest: SignUpRequest

    @Mock
    lateinit var mvoView: MVPView

    private lateinit var signUpPresenterImp: SignUpPresenterImp


    @Test
    fun signUpModelTest() {
        signUpRequest = SignUpRequest()
    }
}