package com.example.taskgithub

import com.example.taskgithub.Data.ApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {

    companion object{
        fun getInstance(): ApiServices {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/").build().create(ApiServices::class.java)
        }
    }

}