package com.example.taskgithub.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.taskgithub.Client
import com.example.taskgithub.Data.Models.Owner
import com.example.taskgithub.Data.Models.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    var apiServices = Client.getInstance()

    fun getUserInfo(username: String): LiveData<Owner> {

        var owner: MutableLiveData<Owner> = MutableLiveData()

        apiServices.getUserInfo(username).enqueue(object : Callback<Owner> {
            override fun onFailure(call: Call<Owner>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Owner>?, response: Response<Owner>?) {
                owner.value = response?.body()
            }
        })
        return owner
    }

    fun getUserRepos(username: String) : LiveData<List<Repo>>{

        var repos : MutableLiveData<List<Repo>> = MutableLiveData()
        apiServices.getUserRepos(username).enqueue(object : Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                repos.value = response?.body()
            }

        })
        return repos
    }
}