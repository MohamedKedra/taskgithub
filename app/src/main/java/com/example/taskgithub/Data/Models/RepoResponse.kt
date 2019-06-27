package com.example.taskgithub.Data.Models

import com.example.taskgithub.Data.Models.Repo
import com.google.gson.annotations.SerializedName

class RepoResponse(
    @SerializedName("items")
    val repos :List<Repo>
)