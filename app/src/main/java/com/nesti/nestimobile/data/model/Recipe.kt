package com.nesti.nestimobile.data.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val name: String = "",
    @SerializedName("difficulty")
    val email: String = "",
    @SerializedName("author")
    val avatar: String = "",
     @SerializedName("image")
    val image: String = ""
)