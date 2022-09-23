package com.example.cambak.cambak.common.util

import org.apache.tomcat.util.codec.binary.Base64
import java.nio.ByteBuffer
import java.time.LocalDateTime
import java.util.*

object CommonUtils {
    fun uuid(): String{
        val uuid = UUID.randomUUID()

        val bb = ByteBuffer.wrap(ByteArray(16))
        bb.putLong(uuid.mostSignificantBits)
        bb.putLong(uuid.leastSignificantBits)

        return Base64.encodeBase64URLSafeString(bb.array())
    }

    fun setNowAsLocalDateTime(): LocalDateTime {
        return LocalDateTime.now()
    }

    fun setNowAsDateTime():Date{
        return Date()
    }
}