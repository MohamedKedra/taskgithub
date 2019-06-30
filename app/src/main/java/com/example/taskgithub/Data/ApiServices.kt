package com.example.taskgithub.Data

import com.example.taskgithub.Data.Models.Owner
import com.example.taskgithub.Data.Models.Repo
import com.example.taskgithub.Data.Models.RepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("search/repositories")
    fun searchInRepos(@Query("q") text: String): Call<RepoResponse>

    @GET("users/{username}")
    fun getUserInfo(@Path("username") username: String): Call<Owner>

    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Call<List<Repo>>
}