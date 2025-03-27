package ru.mobile.data.retrofit


import retrofit2.Call
import retrofit2.http.GET
import ru.mobile.data.models.CourseData

interface RetrofitService {
    @GET("uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    fun getCourses(): Call<CourseData?>
}