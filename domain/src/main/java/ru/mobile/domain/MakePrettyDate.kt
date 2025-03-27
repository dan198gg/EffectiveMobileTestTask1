package ru.mobile.domain

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class MakePrettyDate(val date: String) {
    fun date(): String{
        val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd",  Locale("ru"))
        val date = LocalDate.parse(date , firstApiFormat)
        return "${date.dayOfMonth} ${date.month} ${date.year}"
    }
}