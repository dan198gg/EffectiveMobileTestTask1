package ru.mobile.data.repository

import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import ru.mobile.data.models.Course
import ru.mobile.data.models.CourseData
import ru.mobile.data.room.MainDB

interface MobileRepossitory {
    suspend fun getCourses(mutableLiveData: MutableLiveData<CourseData?>, db: MainDB, state: MutableState<MutableList<Course?>>)
    fun createRoomDB():MainDB
    fun editSharegPref(key: String, value: String)
}