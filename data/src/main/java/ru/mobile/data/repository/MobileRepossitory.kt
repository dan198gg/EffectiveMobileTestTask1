package ru.mobile.data.repository

import androidx.lifecycle.MutableLiveData
import ru.mobile.data.models.CourseData

interface MobileRepossitory {
    suspend fun getCourses(mutableLiveData: MutableLiveData<CourseData?>)
}