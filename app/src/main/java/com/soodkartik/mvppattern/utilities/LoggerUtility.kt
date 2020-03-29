package com.soodkartik.mvppattern.utilities

import android.util.Log

class LoggerUtility {
    companion object {

        /**
         * Sends a Debug message to LogCat and to a log file.
         * @param logMessageTag A tag identifying a group of log messages. Should be a constant in the
         * class calling the logger.
         * @param logMessage The message to add to the log.
         */
        fun d(logMessageTag: String, logMessage: String) {
            val logResult = Log.d(logMessageTag, logMessage)
            if (logResult > 0)
                logToFile(logMessageTag, logMessage)
        }


        /**
         * Sends an error message to LogCat and to a log file.
         * @param logMessageTag A tag identifying a group of log messages. Should be a constant in the
         * class calling the logger.
         * @param logMessage The message to add to the log.
         */
        fun e(logMessageTag: String, logMessage: String) {
            val logResult = Log.e(logMessageTag, logMessage)
            if (logResult > 0)
                logToFile(logMessageTag, logMessage)
        }

        /**
         * Sends an info message and the exception to LogCat and to a log file.
         * @param logMessageTag A tag identifying a group of log messages. Should be a constant in the
         * class calling the logger.
         * @param logMessage The message to add to the log.
         */
        fun i(logMessageTag: String, logMessage: String) {
            val logResult = Log.i(logMessageTag, logMessage)
            if (logResult > 0)
                logToFile(logMessageTag, logMessage)
        }

        /**
         * Sends an warn message and the exception to LogCat and to a log file.
         * @param logMessageTag A tag identifying a group of log messages. Should be a constant in the
         * class calling the logger.
         * @param logMessage The message to add to the log.
         */
        fun w(logMessageTag: String, logMessage: String) {
            val logResult = Log.w(logMessageTag, logMessage)
            if (logResult > 0)
                logToFile(logMessageTag, logMessage)
        }


        /**
         * Sends a message to LogCat and to a log file.
         * @param logMessageTag A tag identifying a group of log messages. Should be a constant in the
         * class calling the logger.
         * @param logMessage The message to add to the log.
         */
        fun v(logMessageTag: String, logMessage: String) {
            val logResult = Log.v(logMessageTag, logMessage)
            if (logResult > 0)
                logToFile(logMessageTag, logMessage)
        }

        /**
         * Writes a message to the log file on the device.
         * @param logMessageTag A tag identifying a group of log messages.
         * @param logMessage The message to add to the log.
         */
        private fun logToFile(
            @Suppress("UNUSED_PARAMETER") logMessageTag: String,
            @Suppress("UNUSED_PARAMETER") logMessage: String
        ) {

        }

    }

}