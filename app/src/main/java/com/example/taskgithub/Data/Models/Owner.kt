package com.example.taskgithub.Data.Models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Owner(
    val id: Int,
    @SerializedName("login")
    var name :String,
    @SerializedName("avatar_url")
    var avatar:String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Owner> {
        override fun createFromParcel(parcel: Parcel): Owner {
            return Owner(parcel)
        }

        override fun newArray(size: Int): Array<Owner?> {
            return arrayOfNulls(size)
        }
    }
}