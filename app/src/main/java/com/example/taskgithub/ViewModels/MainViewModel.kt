package com.example.taskgithub.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.taskgithub.Client
import com.example.taskgithub.Data.ApiServices
import com.example.taskgithub.Data.Models.Repo
import com.example.taskgithub.Data.Models.RepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {


    var apiServices: ApiServices = Client.getInstance()

    fun getAllRepos(text: String): LiveData<List<Repo>> {

        var list: MutableLiveData<List<Repo>> = MutableLiveData()

        apiServices.searchInRepos(text).enqueue(object : Callback<RepoResponse> {
            override fun onFailure(call: Call<RepoResponse>?, t: Throwable?) {
                Log.d("response : ", t?.message)
            }

            override fun onResponse(call: Call<RepoResponse>?, response: Response<RepoResponse>?) {

                if (response?.isSuccessful!!) {

                    list.value = response.body().repos
                }
            }
        })
        return list
    }


}