package com.nesti.nestimobile.data.model

import com.google.gson.annotations.SerializedName

class Paragraph (
        @SerializedName("idParagraph")
        var idParagraph: Int = 0,
        @SerializedName("content")
        var content: String = "",
        @SerializedName("paragraphPosition")
        var paragraphPosition: Int = 0,
        @SerializedName("difficulty")
        var recipePosition: Int = 0,
        @SerializedName("idRecipe")
        var idRecipe: Int = 0
)