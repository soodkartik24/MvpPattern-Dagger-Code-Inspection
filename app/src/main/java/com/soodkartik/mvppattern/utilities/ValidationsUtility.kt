package com.soodkartik.mvppattern.utilities

import com.soodkartik.mvppattern.constants.Constants
import java.util.regex.Pattern

class ValidationsUtility {
    companion object {
        /**
         * Check Field is Empty or not
         */
        fun isFieldNotEmpty(pString: String?): Boolean {
            if (pString.isNullOrEmpty()) {
                return false
            }
            return true
        }

        /**
         * Check Email is valid or not
         * */
        fun isValidEmailAddress(pEmail: String? = Constants.sEmptyString): Boolean {
            val pattern = Pattern.compile(Constants.Pattern.sEmailRegularExpression)
            val matcher = pattern.matcher(Constants.sEmptyString + pEmail)
            return matcher.matches()
        }
    }
}