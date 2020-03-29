package com.soodkartik.mvppattern.modules.dashboard.presenter

import com.soodkartik.mvppattern.base.presenter.BasePresenterImp
import com.soodkartik.mvppattern.base.presenter.MVPView
import javax.inject.Inject

class DashboardPresenterImp
@Inject internal constructor(pMVPView: MVPView) : BasePresenterImp(pMVPView), DashboardPresenter