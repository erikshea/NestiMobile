package com.nesti.nestimobile.data.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("idRecipe")
    val idRecipe: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("difficulty")
    val difficulty: Int = 0,
    @SerializedName("author")
    val author: String = "",
     @SerializedName("image")
    val image: String = ""
)