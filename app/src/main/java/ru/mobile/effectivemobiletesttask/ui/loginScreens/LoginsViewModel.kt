package ru.mobile.effectivemobiletesttask.ui.loginScreens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mobile.data.models.CourseData
import ru.mobile.data.repository.MobileRepossitory
import ru.mobile.data.sharedpref.SharedPref
import ru.mobile.domain.CheckCorrectEmailAndPass
import ru.mobile.domain.MobileRepositoryImpl

class LoginsViewModel(val context: Context, val mobileRepossitory: MobileRepossitory): ViewModel() {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SharedPref.NAME, Context.MODE_PRIVATE)
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    companion object {
        val mutableLiveData: MutableLiveData<CourseData?> =
            MutableLiveData(CourseData(mutableListOf()))
    }
    fun saveName(){
        mobileRepossitory.editSharegPref(SharedPref.FLAG, "1")
    }
    fun check(): Boolean{
         return CheckCorrectEmailAndPass(email.value, password.value).check()
    }
    init {

    }

}