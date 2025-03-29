package ru.mobile.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import ru.mobile.data.models.Course


@Entity
data class CourseEntity(
    @PrimaryKey(false)
    val id:Int?,
    var hasLike: Boolean?,
    val price: String?,
    val publishDate: String?,
    val rate: String?,
    val startDate: String?,
    val text: String?,
    val title: String?

)

//class DateConverter {
//    @TypeConverter
//    fun myObjectsToStoredString(course: Course?): String {
//        val gson = Gson();
//        return gson.toJson(course)
//    }
//}
