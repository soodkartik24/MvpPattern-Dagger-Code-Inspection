package com.soodkartik.mvppattern.utilities

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


object CommonUtility {

    fun Context.hideKeyboard(view: View?) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        if (inputMethodManager != null && view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}