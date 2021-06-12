package com.nesti.nestimobile.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Tag entity
 * -serializeable to and from JSON
 * -parcelable for intent putExtra
 */
data class Tag(
    @SerializedName("idTag")
    var idTag: Int = 0,
    @SerializedName("name")
    var name: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this() {
        idTag = parcel.readInt()
        name = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idTag);
        parcel.writeString(name);
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tag> {
        override fun createFromParcel(parcel: Parcel): Tag {
            return Tag(parcel)
        }

        override fun newArray(size: Int): Array<Tag?> {
            return arrayOfNulls(size)
        }
    }
}