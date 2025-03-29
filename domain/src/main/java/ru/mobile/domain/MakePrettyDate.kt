package ru.mobile.domain

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class MakePrettyDate(val date: String) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun date(): String{
//        val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd",  Locale("RU"))
//        val date = LocalDate.parse(date , firstApiFormat)
//        return "${date.dayOfMonth} ${date.month} ${date.year}"


        val dateParser = SimpleDateFormat("yyyy-MM-dd")
        val date = dateParser.parse(date)
        val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
        val formattedDate = dateFormatter.format(date!!)
        return formattedDate
    }
}