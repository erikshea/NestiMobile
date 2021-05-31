package com.nesti.nestimobile.data.model

import android.R.attr.description
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class IngredientRecipe(
        @SerializedName("idIngredient")
        var idIngredient: Int = 0,
        @SerializedName("idRecipe")
        var idRecipe: Int = 0,
        @SerializedName("quantity")
        var quantity: Double = 0.0,
        @SerializedName("recipePosition")
        var recipePosition: Int = 0,
        @SerializedName("unitName")
        var unitName: String = "",
        @SerializedName("name")
        var name: String = ""
)