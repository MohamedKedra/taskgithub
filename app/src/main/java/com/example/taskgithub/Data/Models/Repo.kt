package com.example.taskgithub.Data.Models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Repo(
    val id: Int? = null,
    val name: String? = null,
    val owner: Owner? = null,
    val forks:Int? = null,
    @SerializedName("default_branch")
    val branch:String? = null,
    val language:String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Owner::class.java.classLoader),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )



    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeInt(id!!)
        parcel?.writeString(name)
        parcel?.writeParcelable(owner,p1)
        parcel?.writeInt(forks!!)
        parcel?.writeString(branch)
        parcel?.writeString(language)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repo> {
        override fun createFromParcel(parcel: Parcel): Repo {
            return Repo(parcel)
        }

        override fun newArray(size: Int): Array<Repo?> {
            return arrayOfNulls(size)
        }
    }
}