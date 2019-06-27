package com.example.taskgithub.Data.Models

import android.os.Parcel
import android.os.Parcelable

class Repo(
    val id: Int,
    val name: String,
    val owner: Owner
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Owner::class.java.classLoader)
    )

    override fun writeToParcel(p0: Parcel?, p1: Int) {
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