package ru.mobile.data.repository

import androidx.lifecycle.MutableLiveData
import ru.mobile.data.models.CourseData
import ru.mobile.data.room.MainDB

interface MobileRepossitory {
    suspend fun getCourses(mutableLiveData: MutableLiveData<CourseData?>)
    fun createRoomDB():MainDB
    fun editSharegPref(key: String, value: String)
}