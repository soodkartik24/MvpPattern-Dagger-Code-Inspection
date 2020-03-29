package com.soodkartik.mvppattern.utilities

import android.app.TimePickerDialog
import android.content.Context
import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.utilities.interfaces.TimePickerInterface
import java.util.*

object TimePickerUtility {
    fun showTimerPicker(
        pContext: Context,
        pTimePickerInterface: TimePickerInterface,
        pDate: Date? = null
    ) {
        val calender = Calendar.getInstance()
        var mHourOfDay = calender.get(Calendar.HOUR_OF_DAY)
        var mMinute = calender.get(Calendar.MINUTE)

        if (pDate != null) {
            calender.time = pDate
            mHourOfDay = calender.get(Calendar.HOUR_OF_DAY)
            mMinute = calender.get(Calendar.MINUTE)
        }
        // Launch Time Picker Dialog
        val timePickerDialog = TimePickerDialog(
            pContext, R.style.DialogTheme,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                pTimePickerInterface.onTimeGet(hourOfDay, minute)
            },
            mHourOfDay,
            mMinute,
            false
        )
        timePickerDialog.show()
    }
}