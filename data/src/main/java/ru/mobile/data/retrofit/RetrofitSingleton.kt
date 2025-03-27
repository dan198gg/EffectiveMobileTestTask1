package ru.mobile.data.retrofit

import retrofit2.create


class RetrofitSingleton {
    companion object{
        val url = "https://drive.usercontent.google.com/u/0/"
        val client = RetrofitClient.getClient(url).create(RetrofitService::class.java)
    }
}