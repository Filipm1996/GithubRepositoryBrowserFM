package com.example.githubrepositorybrowserfm.data.entities

import android.os.Parcel
import android.os.Parcelable

data class RepositoryInfo(
    val name: String?,
    val description: String?,
    val commitsQty: Int,
    val issuesQty: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(commitsQty)
        parcel.writeInt(issuesQty)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepositoryInfo> {
        override fun createFromParcel(parcel: Parcel): RepositoryInfo {
            return RepositoryInfo(parcel)
        }

        override fun newArray(size: Int): Array<RepositoryInfo?> {
            return arrayOfNulls(size)
        }
    }
}
