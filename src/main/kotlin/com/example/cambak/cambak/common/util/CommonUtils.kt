package com.example.cambak.cambak.common.util

import com.example.cambak.database.entity.place.Region
import org.apache.tomcat.util.codec.binary.Base64
import java.nio.ByteBuffer
import java.time.LocalDateTime
import java.util.*
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

    fun getRegions(): List<String> {
        return listOf(
//            Region.SEOULANDGYEONGGI,Region.INCHEON,Region.GANGWON,Region.CHUNGBUK,Region.CHUNGNAM,
//            Region.GYEONGNAM,Region.GYEONGBUK,Region.JEONBUK,Region.JEONNAM,Region.JEJU
            "SEOULANDGYEONGGI", "INCHEON", "GANGWON", "CHUNGBUK", "CHUNGNAM",
            "GYEONGNAM", "GYEONGBUK", "JEONBUK", "JEONNAM", "JEJU"
        )
    }
    fun getCarType(): List<String>{
        return listOf(
            "BASIC","CARAVAN","TRAILER","MOTERHUM"
        )
    }

    fun getEnvType(): List<String>{
        return listOf(
            "MT","SEA","VALLEY"
        )
    }

    fun getCarTypePossibility(type: List<String>, placeType: CharArray):Boolean{
        if(type.isNullOrEmpty()) return false
        return when(type[0]){
            "BASIC" -> placeType[0] == '1'
            "TRAILER" -> placeType[1] == '1'
            "CARAVAN" -> placeType[2] == '1'
            "MOTERHUM" -> placeType[3] == '1'
            else -> false
        }
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