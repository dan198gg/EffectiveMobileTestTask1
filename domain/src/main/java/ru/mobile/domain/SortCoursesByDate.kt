package ru.mobile.domain

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import ru.mobile.data.models.Course
import ru.mobile.data.models.CourseData
import ru.mobile.data.room.CourseEntity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SortCoursesByDate(var lst: MutableLiveData<CourseData?>) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun sortCoursesByDate(): MutableLiveData<CourseData?>  {
        val dateTimeStrToLocalDateTime: (Course?) -> LocalDate = {
            LocalDate.parse(it?.publishDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        }
        lst.value?.courses = lst.value?.courses?.sortedBy(dateTimeStrToLocalDateTime)
        Log.i("FEASFAWFAWF", lst.value?.courses!!.toString())
        return lst
    }
 }

class SortLikedCourses(var lstLiked: MutableList<CourseEntity?>){
    @RequiresApi(Build.VERSION_CODES.O)
    fun sortLikedCoursesByDate(): MutableList<CourseEntity?> {
        val dateTimeStrToLocalDateTime: (CourseEntity?) -> LocalDate = {
            LocalDate.parse(it?.publishDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        }
        lstLiked = lstLiked.sortedBy(dateTimeStrToLocalDateTime).toMutableList()
        return lstLiked
    }
}