@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.soodkartik.mvppattern.utilities

import android.annotation.SuppressLint
import com.soodkartik.mvppattern.constants.Constants
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateFormatterUtility {
    val currentTimeInMilliSeconds: Long
        get() = calendarInstance.time.time

    val currentTimeInSeconds: Long
        get() = calendarInstance.time.time / 1000

    private val calendarInstance: Calendar
        get() = Calendar.getInstance()

    fun convertLocalTimeToUTC(
        utcTimeFormat: String?,
        datetime: Date?
    ): String {
        val simpleDateFormat = SimpleDateFormat(utcTimeFormat)
        simpleDateFormat.timeZone = TimeZone.getTimeZone(Constants.DateFormat.sUTC_FORMAT)
        return simpleDateFormat.format(datetime?.time)
    }

    fun convertUTCToLocalTime(time: String?, dateFormat: String?): Date? {
        try {
            val simpleDateFormat =
                SimpleDateFormat(dateFormat)
            simpleDateFormat.timeZone = TimeZone.getTimeZone(Constants.DateFormat.sUTC_FORMAT)
            val date = simpleDateFormat.parse(time)
            simpleDateFormat.timeZone = TimeZone.getDefault()
            return simpleDateFormat.parse(simpleDateFormat.format(date))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    fun convertMillisecondsToDate(milliseconds: Long): Date {
        return Date(milliseconds)
    }

    fun getDateAsString(dateFormat: String?, date: Date?): String {
        val simpleDateFormat = SimpleDateFormat(dateFormat)
        return simpleDateFormat.format(date)
    }

    fun getDateAsDate(dateFormat: String?, date: Date?): Date? {
        val simpleDateFormat = SimpleDateFormat(dateFormat)
        val format = simpleDateFormat.format(date)
        try {
            return simpleDateFormat.parse(format)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    val dateInstance: Date
        get() = Date()

    fun getStringAsDate(dateFormat: String?, date: String?): Date? {
        val simpleDateFormat = SimpleDateFormat(dateFormat)
        try {
            return simpleDateFormat.parse(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun convertDurationToMinAndSeconds(time: Long): String {
        val seconds = time % 60
        var hours = time / 60
        val minutes = hours % 60
        hours /= 60
        return when {
            hours > 0 -> {
                Constants.sEmptyString + hours + "h " + minutes + "m " + seconds + "s"
            }
            minutes > 0 -> {
                Constants.sEmptyString + minutes + "m " + seconds + "s"
            }
            else -> {
                Constants.sEmptyString + seconds + "s"
            }
        }
    }

    val currentTimeZone: TimeZone
        get() {
            val cal = Calendar.getInstance()
            return cal.timeZone
        }

    fun getDate(pDayOfMonth: Int, pYear: Int, pMonth: Int): Date {
        val calendar = calendarInstance
        calendar.set(Calendar.YEAR, pYear)
        calendar.set(Calendar.MONTH, pMonth)
        calendar.set(Calendar.DAY_OF_MONTH, pDayOfMonth)
        return calendar.time
    }
}