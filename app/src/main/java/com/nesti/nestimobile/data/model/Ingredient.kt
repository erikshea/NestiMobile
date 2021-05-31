package com.nesti.nestimobile.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("idIngredient")
    var idIngredient: Int = 0,
    @SerializedName("name")
    var name: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this() {
        idIngredient = parcel.readInt()
        name = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idIngredient);
        parcel.writeString(name);
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ingredient> {
        override fun createFromParcel(parcel: Parcel): Ingredient {
            return Ingredient(parcel)
        }

        override fun newArray(size: Int): Array<Ingredient?> {
            return arrayOfNulls(size)
        }
    }
}