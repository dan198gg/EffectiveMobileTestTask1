package ru.mobile.effectivemobiletesttask.ui.loginScreens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mobile.data.models.CourseData
import ru.mobile.domain.CheckCorrectEmailAndPass
import ru.mobile.domain.MobileRepositoryImpl

class LoginsViewModel(): ViewModel() {
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    companion object {
        val mutableLiveData: MutableLiveData<CourseData?> =
            MutableLiveData(CourseData(mutableListOf()))
    }

    fun check(): Boolean{
         return CheckCorrectEmailAndPass(email.value, password.value).check()
    }
    init {
        CoroutineScope(Dispatchers.IO).launch {
            MobileRepositoryImpl().getCourses(mutableLiveData)
        }
    }

}