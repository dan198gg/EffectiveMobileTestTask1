package ru.mobile.domain

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

class MobileRepositoryImpl: MobileRepossitory {
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
}