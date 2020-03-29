package com.soodkartik.mvppattern.utilities

import io.michaelrocks.libphonenumber.android.NumberParseException
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import io.michaelrocks.libphonenumber.android.Phonenumber.PhoneNumber


class PhoneNumberUtility(private val util: PhoneNumberUtil?) {
    fun getFormmatedNumber(userNumber: String?): String? {
        return getFormmatedNumber(util, userNumber)
    }

    fun getFormmatedNumberAsE164String(phoneNumber: PhoneNumber?): String? {
        return getFormmatedNumberAsE164String(util, phoneNumber)
    }

    fun getFormmatedName(userNumber: String?): String? {
        return getFormmatedName(util, userNumber)
    }

    fun getE164String(userNumber: String?): String? {
        return getE164String(util, userNumber)
    }

    fun isValidNumber(userNumber: String?): Boolean {
        return isValidNumber(util, userNumber)
    }

    fun getPhoneNumberInstance(userNumber: String?): PhoneNumber? {
        return getPhoneNumberInstance(util, userNumber)
    }

    companion object {


        fun getFormmatedNumber(util: PhoneNumberUtil?, userNumber: String?): String? {
            if (util != null) {
                try {
                    val phoneNumber = util.parse(userNumber, "US")
                    return util.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
                } catch (e: NumberParseException) {
                    e.printStackTrace()
                }
            }
            return null
        }

        fun getFormmatedNumberAsE164String(
            util: PhoneNumberUtil?,
            phoneNumber: PhoneNumber?
        ): String? {
            return util?.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164)
        }

        fun getFormmatedName(util: PhoneNumberUtil?, userNumber: String?): String? {
            if (util != null) {
                try {
                    val phoneNumber = util.parse(userNumber, "IN")
                    return util.getRegionCodeForNumber(phoneNumber)
                } catch (e: NumberParseException) {
                    e.printStackTrace()
                }
            }
            return null
        }

        fun getE164String(util: PhoneNumberUtil?, userNumber: String?): String? {
            if (util != null) {
                try {
                    val phoneNumber = util.parse(userNumber, "IN")
                    return util.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164)
                } catch (e: NumberParseException) {
                    e.printStackTrace()
                }
            }
            return null
        }

        fun isValidNumber(util: PhoneNumberUtil?, userNumber: String?): Boolean {
            return if (util != null) {
                try {
                    val phoneNumber = util.parse(userNumber, "IN")
                    util.isValidNumber(phoneNumber)
                } catch (e: NumberParseException) {
                    e.printStackTrace()
                    false
                }
            } else false
        }

        fun getPhoneNumberInstance(util: PhoneNumberUtil?, userNumber: String?): PhoneNumber? {
            return if (util != null) {
                try {
                    util.parse(userNumber, "IN")
                } catch (e: NumberParseException) {
                    e.printStackTrace()
                    null
                }
            } else null
        }
    }
}