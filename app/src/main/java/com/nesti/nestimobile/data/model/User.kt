package com.nesti.nestimobile.data.model

import com.google.gson.annotations.SerializedName

/**
 * User entity
 * -serializeable to and from JSON
 */
data class User(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("avatar")
    val avatar: String = ""
)