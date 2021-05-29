package com.nesti.nestimobile.data.model

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("idTag")
    val idTag: Int = 0,
    @SerializedName("name")
    val name: String = ""
)