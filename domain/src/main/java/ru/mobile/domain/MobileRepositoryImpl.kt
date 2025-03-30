package ru.mobile.domain

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateSet
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mobile.data.models.Course
import ru.mobile.data.models.CourseData
import ru.mobile.data.repository.MobileRepossitory
import ru.mobile.data.retrofit.RetrofitSingleton
import ru.mobile.data.room.CourseEntity
import ru.mobile.data.room.MainDB
import ru.mobile.data.sharedpref.SharedPref

class MobileRepositoryImpl(val context: Context): MobileRepossitory {
    override suspend fun getCourses(mutableLiveData: MutableLiveData<CourseData?>, db: MainDB, state: MutableState<MutableList<Course?>>,
                                    mutableSnap: SnapshotStateSet<Course?>
    ) {
        var courses:MutableLiveData<MutableList<CourseData?>?>? = null
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitSingleton.client.getCourses().enqueue(object : Callback<CourseData?> {

                override fun onResponse(p0: Call<CourseData?>, p1: Response<CourseData?>) {
                    CoroutineScope(Dispatchers.IO).launch {
                        for (el in p1.body()?.courses!!) {
                            if (el?.hasLike == true) {
                                db.dao.insertNote(
                                    CourseEntity(
                                        el.id,
                                        el.hasLike,
                                        el.price,
                                        el.publishDate,
                                        el.rate,
                                        el.startDate,
                                        el.text,
                                        el.title
                                    )
                                )

                            }
                        }
                        for (el in db.dao.getAllNotes()) {
                            mutableSnap.add(
                                Course(
                                    el.hasLike,
                                    el.id,
                                    el.price,
                                    el.publishDate,
                                    el.rate,
                                    el.startDate,
                                    el.text,
                                    el.title
                                )
                            )
                            Log.i("STATE", mutableSnap.toString())
                        }
                    }
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