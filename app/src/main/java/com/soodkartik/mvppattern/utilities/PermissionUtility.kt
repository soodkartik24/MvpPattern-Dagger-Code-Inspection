package com.soodkartik.mvppattern.utilities

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Make permission utility for checking permissions
 */
class PermissionUtility {
    private fun useRunTimePermissions(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
    }

    fun hasPermission(activity: AppCompatActivity, permission: String): Boolean {
        return if (useRunTimePermissions()) {
            activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        } else true
    }

    fun requestPermissions(
        pActivity: AppCompatActivity,
        permission: Array<String>,
        requestCode: Int
    ) {
        if (useRunTimePermissions()) {
            pActivity.requestPermissions(permission, requestCode)
        }
    }

    fun requestPermissions(pFragment: Fragment, permission: Array<String>, requestCode: Int) {
        if (useRunTimePermissions()) {
            pFragment.requestPermissions(permission, requestCode)
        }
    }

    private fun shouldShowRational(activity: AppCompatActivity, permission: String): Boolean {
        return if (useRunTimePermissions()) {
            activity.shouldShowRequestPermissionRationale(permission)
        } else false
    }

    fun shouldAskForPermission(activity: AppCompatActivity, permission: String): Boolean {
        return if (useRunTimePermissions()) {
            !hasPermission(activity, permission) && shouldShowRational(activity, permission)
        } else false
    }

    fun goToAppSettings(activity: AppCompatActivity) {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", activity.packageName, null)
        )
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
    }

}