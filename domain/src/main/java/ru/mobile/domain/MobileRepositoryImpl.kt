package ru.mobile.domain

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mobile.data.models.CourseData
import ru.mobile.data.repository.MobileRepossitory
import ru.mobile.data.retrofit.RetrofitSingleton
import ru.mobile.data.room.MainDB
import ru.mobile.data.sharedpref.SharedPref

class MobileRepositoryImpl(val context: Context): MobileRepossitory {
    override suspend fun getCourses(mutableLiveData: MutableLiveData<CourseData?>){
        var courses:MutableLiveData<MutableList<CourseData?>?>? = null
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitSingleton.client.getCourses().enqueue(object : Callback<CourseData?> {
                override fun onResponse(p0: Call<CourseData?>, p1: Response<CourseData?>) {
                    mutableLiveData.value = p1.body()
                    Log.i("COURSES", p1.body().toString())
                    Log.i("MUTABLELIVEDATA", mutableLiveData.value?.courses.toString())

                }

                override fun onFailure(p0: Call<CourseData?>, p1: Throwable) {
                    Log.i("ERRRRRROOOOOR", p1.message.toString())
                }
            })


        }


    }

    override fun createRoomDB(): MainDB {
            val database by lazy { MainDB.createDB(context) }
        return database
    }

    override fun editSharegPref(key: String, value: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SharedPref.NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
}