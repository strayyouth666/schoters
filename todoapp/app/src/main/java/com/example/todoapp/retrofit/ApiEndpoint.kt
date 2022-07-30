package com.example.todoapp.retrofit

import com.example.todoapp.MainModel
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {

    @GET("aa6b7dd63ddc48a19510e77486e9da12")
    fun data(): Call<MainModel>
}