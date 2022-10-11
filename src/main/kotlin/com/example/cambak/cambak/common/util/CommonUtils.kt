package com.example.cambak.cambak.common.util

import org.apache.tomcat.util.codec.binary.Base64
import java.nio.ByteBuffer
import java.time.LocalDateTime
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.jvm.Throws

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

    // 전화번호 010-9999-9999 -> 01099999999
    @Throws(Exception::class)
    fun mobileNoParsing(mobileNo: String) : String{
        val pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}")
        val matchar = pattern.matcher(mobileNo)
        if(!matchar.matches()) throw Exception("Invalid mobildNo")
        return mobileNo.replace("-","")
    }
}