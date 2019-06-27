package com.example.taskgithub.Data

import com.example.taskgithub.Data.Models.RepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("search/repositories")
    fun searchInRepos(@Query("q") text:String) : Call<RepoResponse>
}