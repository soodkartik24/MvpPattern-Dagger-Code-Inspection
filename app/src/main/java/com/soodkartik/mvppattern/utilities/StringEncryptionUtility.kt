package com.soodkartik.mvppattern.utilities

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and
import kotlin.text.Charsets.ISO_8859_1

object StringEncryptionUtility {
    @Throws(NoSuchAlgorithmException::class)
    fun getData(text: String?): String? {
        val algorithmType = "SHA-1"
        val md: MessageDigest = MessageDigest.getInstance(algorithmType)
        md.update(text?.toByteArray(ISO_8859_1)!!, 0, text.length)
        val sha1hash: ByteArray = md.digest()
        return convertToHex(sha1hash)
    }

    private fun convertToHex(data: ByteArray): String? {
        val buf = StringBuilder()
        for (b in data) {
            var halfByte: Int = (b.toInt() ushr 4) and 0x0F
            var twoHalfs = 0
            do {
                buf.append(if (halfByte in 0..9) ('0'.toInt() + halfByte).toChar() else ('a'.toInt() + (halfByte - 10)).toChar())
                halfByte = (b and 0x0F).toInt()
            } while (twoHalfs++ < 1)
        }
        return buf.toString()
    }
}