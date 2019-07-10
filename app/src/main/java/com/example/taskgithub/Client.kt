package com.example.taskgithub

import com.example.taskgithub.Data.ApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//client class not nessisary cause of it contain only singletone method
//todo convert class to singletone
class Client {

    //getInstance always build new retrofit instance not return the already build one
    //todo fix singletone issue
    companion object{

        fun getInstance(): ApiServices {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/").build().create(ApiServices::class.java)
        }
    }

}