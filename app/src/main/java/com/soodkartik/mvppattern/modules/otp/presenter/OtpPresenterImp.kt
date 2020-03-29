package com.soodkartik.mvppattern.modules.otp.presenter

import com.soodkartik.mvppattern.base.presenter.BasePresenterImp
import com.soodkartik.mvppattern.base.presenter.MVPView
import javax.inject.Inject

class OtpPresenterImp
@Inject internal constructor(private val pMVPView: MVPView) : BasePresenterImp(pMVPView),
    OtpPresenter