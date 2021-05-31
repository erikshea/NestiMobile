package com.nesti.nestimobile.data.model

import android.R.attr.description
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Recipe(
        @SerializedName("idRecipe")
        var idRecipe: Int = 0,
        @SerializedName("name")
        var name: String = "",
        @SerializedName("difficulty")
        var difficulty: Int = 0,
        @SerializedName("author")
        var author: String = "",
        @SerializedName("image")
        var image: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this() {
        idRecipe = parcel.readInt()
        name = parcel.readString().toString()
        difficulty = parcel.readInt()
        author = parcel.readString().toString();
        image = parcel.readString().toString();
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idRecipe);
        parcel.writeString(name);
        parcel.writeInt(difficulty);
        parcel.writeString(author);
        parcel.writeString(image);
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}