package com.soodkartik.mvppattern.modules.launcher.presenter

import com.soodkartik.mvppattern.base.presenter.MVPView

class SplashPresenterImp(private var mvpView: MVPView?) : SplashPresenter {
    override fun onViewCreated() {
        mvpView?.finishView()
    }

    override fun onViewDestroyed() {
        mvpView = null
    }

}