package com.example.taskgithub.Data.Models

import com.google.gson.annotations.SerializedName

class RepoResponse(
    @SerializedName("total_count")
    val totalCount : Int,
    @SerializedName("items")
    val repos :List<Repo>
)