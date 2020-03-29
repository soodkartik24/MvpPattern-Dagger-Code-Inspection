package com.soodkartik.mvppattern.utilities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.constants.Constants
import com.google.android.material.snackbar.Snackbar

// To show the toast
fun Context.showToast(
    pMessage: String?
) {
    Toast.makeText(this, pMessage, Toast.LENGTH_SHORT).show()
}

class UserAlertUtility {
    companion object {
        private var mAlertDialog: AlertDialog? = null
        private var mCustomDialog: AppCompatDialog? = null
        fun showSnackBar(
            pMessage: String?,
            pView: View?,
            pContext: Context?
        ) {
            if (pView != null) {
                val snackbar = Snackbar.make(pView, pMessage!!, Snackbar.LENGTH_SHORT)
                val sbView = snackbar.view
                val textView =
                    sbView.findViewById<TextView>(R.id.snackbar_text)
                textView.setTextColor(ContextCompat.getColor(pContext!!, R.color.colorBlack))
                textView.maxLines = Constants.sSNACKBAR_MAX_LINES
                sbView.setBackgroundColor(ContextCompat.getColor(pContext, R.color.colorWhite))
                snackbar.show()
            }
        }


        // Show a dialog alert for some actions
        fun showAlertDialog(
            pTitle: String?,
            pMessage: String?,
            pContext: Activity?,
            pPositiveButton: String? = Constants.sEmptyString,
            pNegativeButton: String? = Constants.sEmptyString
        ) {
            if (pContext != null) {
                if (pContext.isFinishing) {
                    return
                }
                /*
             * If the alert dialog is already showing neednot create a new one
             */if (mAlertDialog == null || !mAlertDialog!!.isShowing) {
                    mAlertDialog =
                        AlertDialog.Builder(pContext, R.style.AlertDialogCustom)
                            .create()
                    if (pTitle == null) {
                        with(mAlertDialog) { this?.setTitle(pContext.getString(R.string.app_name)) }
                    } else {
                        with(mAlertDialog) { this?.setTitle(pTitle) }
                    }
                    mAlertDialog!!.setMessage(pMessage)
                    mAlertDialog!!.setCancelable(false)
                    var positiveText: String? = pContext.getString(R.string.ok_message)
                    mAlertDialog.run {
                        if (pPositiveButton != null && pPositiveButton.isNotEmpty()) {
                            positiveText = pPositiveButton
                        }
                        this!!.setButton(
                            DialogInterface.BUTTON_POSITIVE, positiveText
                        ) { _: DialogInterface?, _: Int ->

                        }
                        if (pNegativeButton != null && pNegativeButton.isNotEmpty()) {
                            this.setButton(
                                DialogInterface.BUTTON_NEGATIVE, pNegativeButton
                            ) { _: DialogInterface?, _: Int -> hideAlertDialog() }
                        }
                        show()
                    }
                }
            }
        }

        // hide progress
        private fun hideAlertDialog() {
            if (mAlertDialog != null && mAlertDialog!!.isShowing) {
                mAlertDialog!!.dismiss()
                mAlertDialog = null
            }
        }

        fun initCustomDialog(
            pContext: Context?,
            pLayout: Int
        ): ViewDataBinding? {
            var binding: ViewDataBinding? = null
            if (mCustomDialog == null || !mCustomDialog?.isShowing!!) {
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(pContext),
                    pLayout,
                    null,
                    false
                )
                mCustomDialog = AppCompatDialog(pContext)
                mCustomDialog?.create()
                mCustomDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                mCustomDialog?.setContentView(binding.root)
                if (mCustomDialog?.window != null) {
                    mCustomDialog?.window?.setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT
                    )
                    mCustomDialog?.window?.setBackgroundDrawable(
                        ColorDrawable(
                            ContextCompat.getColor(pContext!!, R.color.colorTransparentDark)
                        )
                    )
                }
                mCustomDialog?.setCancelable(false)
            }
            return binding
        }

        fun showCustomDialog() {
            if (mCustomDialog != null && !mCustomDialog!!.isShowing) {
                mCustomDialog!!.show()
            }
        }

        fun hideCustomDialog() {
            if (mCustomDialog != null && mCustomDialog!!.isShowing) {
                mCustomDialog!!.dismiss()
                mCustomDialog = null
            }
        }
    }
}