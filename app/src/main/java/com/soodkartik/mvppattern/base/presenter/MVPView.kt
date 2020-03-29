package com.soodkartik.mvppattern.base.presenter

import com.soodkartik.mvppattern.constants.Enums

interface MVPView {
    fun onError(pErrorMessage: String?, pType: Enums.ErrorType, pCode: Int?)

    fun showProgress(pMessage: String, pIsCancelable: Boolean = false)

    fun hideProgress()

    fun onSuccess(pResponse: Any?, pIsPaginatedCall: Boolean?)

    fun onFailure()

    fun finishView()
}