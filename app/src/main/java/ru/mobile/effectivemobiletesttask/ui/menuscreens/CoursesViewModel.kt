package ru.mobile.effectivemobiletesttask.ui.menuscreens

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mobile.data.models.CourseData
import ru.mobile.domain.MakePrettyDate
import ru.mobile.effectivemobiletesttask.ui.loginScreens.LoginsViewModel

class CoursesViewModel():ViewModel() {
    val searchText = mutableStateOf("")
    val mutableLiveData: MutableLiveData<CourseData?> = MutableLiveData(CourseData(mutableListOf()))
    fun prettyDate(date: String): String {
        return MakePrettyDate(date).date()
    }

    init {
        mutableLiveData.value = LoginsViewModel.mutableLiveData.value

    }
}