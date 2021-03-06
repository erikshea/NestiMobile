package com.nesti.nestimobile.data.model

import android.R.attr.description
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * IngredientRecipe entity
 * -serializeable to and from JSON
 */
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
) {
        fun getIngredient():Ingredient{
                return Ingredient(idIngredient=idIngredient,name=name)
        }
}