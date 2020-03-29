package com.soodkartik.mvppattern.modules.login

import com.soodkartik.mvppattern.base.presenter.MVPView
import com.soodkartik.mvppattern.modules.login.models.request.LoginRequest
import com.soodkartik.mvppattern.modules.login.presenter.LoginPresenterImp
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginModelTest {

    private lateinit var loginRequest: LoginRequest

    @Mock
    lateinit var mvoView: MVPView

    private lateinit var loginPresenterImp: LoginPresenterImp

    @Test
    fun testLoginRequestModel() {
        loginRequest = LoginRequest()
        loginRequest.username="kfdlkmfkmadfn"
        loginRequest.password = "dwf23413"
        loginRequest.token = "123134324fewdfsdf314"
        loginPresenterImp = LoginPresenterImp(mvoView)
        Assert.assertEquals(loginPresenterImp.isCredentialsValid(loginRequest), true)
    }

}