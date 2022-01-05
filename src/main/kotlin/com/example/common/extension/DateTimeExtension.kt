package com.example.common.extension

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

const val DateTimeFormatPattern = "YYYY-MM-dd HH:mm:ss"
internal val KST = TimeZone.getTimeZone("Asia/Seoul")!!
internal val KSTFormatter = DateTimeFormatter.ofPattern(DateTimeFormatPattern).withZone(KST.toZoneId())!!

fun OffsetDateTime.toFormatted() = KSTFormatter.format(this)!!
