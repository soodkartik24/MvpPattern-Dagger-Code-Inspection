package com.soodkartik.mvppattern.utilities

import android.content.Context
import android.content.SharedPreferences
import com.soodkartik.mvppattern.application.TravelAppApplication
import com.soodkartik.mvppattern.constants.Constants
import com.soodkartik.mvppattern.constants.Enums
import com.soodkartik.mvppattern.dagger.application.ApplicationScope
import javax.inject.Inject


/**
 * Make Shared Preferences utility for saving local app fields.
 */
@ApplicationScope
class SharedPreferencesUtility @Inject internal constructor(pApplication: TravelAppApplication?) {

    private var mSharedPreferences: SharedPreferences? = null

    init {
        mSharedPreferences = pApplication?.getSharedPreferences(
            Constants.SharedPreferences.sSHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }

    /**
     * set data to sharedPreferences of any type
     * */

    fun setData(
        pType: String,
        pSharedPrefString: String = Constants.sEmptyString,
        pValue: Any
    ) {
        val editor = mSharedPreferences!!.edit()
        when (pType) {
            Enums.SharePreferencesEnum.StringType.name -> editor.putString(
                pSharedPrefString,
                pValue as String?
            )
            Enums.SharePreferencesEnum.IntType.name -> editor.putInt(
                pSharedPrefString,
                pValue as Int
            )
            Enums.SharePreferencesEnum.BooleanType.name -> editor.putBoolean(
                pSharedPrefString,
                pValue as Boolean
            )
            Enums.SharePreferencesEnum.FloatType.name -> editor.putFloat(
                pSharedPrefString,
                pValue as Float
            )
            Enums.SharePreferencesEnum.LongType.name -> editor.putLong(
                pSharedPrefString,
                pValue as Long
            )
        }
        editor.apply()
    }

    /**
     * get data from sharedPreferences of any type
     * */

    fun getData(
        pType: String,
        pSharedPrefString: String = Constants.sEmptyString
    ): Any {
        val data = Constants.sEmptyString
        return when (pType) {
            Enums.SharePreferencesEnum.StringType.name -> mSharedPreferences!!.getString(
                pSharedPrefString,
                Constants.sEmptyString
            )!!
            Enums.SharePreferencesEnum.IntType.name -> mSharedPreferences!!.getInt(
                pSharedPrefString,
                Constants.SharedPreferences.sDEFAULT_INT_VALUE
            )
            Enums.SharePreferencesEnum.BooleanType.name -> mSharedPreferences!!.getBoolean(
                pSharedPrefString,
                Constants.SharedPreferences.sDEFAULT_BOOLEAN_VALUE
            )
            Enums.SharePreferencesEnum.FloatType.name -> mSharedPreferences!!.getFloat(
                pSharedPrefString,
                Constants.SharedPreferences.sDEFAULT_FLOAT_VALUE
            )
            Enums.SharePreferencesEnum.LongType.name -> mSharedPreferences!!.getLong(
                pSharedPrefString,
                Constants.SharedPreferences.sDEFAULT_LONG_VALUE.toLong()
            )
            else -> data
        }
    }

    /**
     * Clear all data from sharedPreferences
     * */
    fun clearAllData() {
        mSharedPreferences!!.edit().clear().apply()
    }
}