package com.nesti.nestimobile.data.api

import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.model.User
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiService  {

    fun getUsers(): Single<List<User>> {
        return Rx2AndroidNetworking.get("https://5e510330f2c0d300147c034c.mockapi.io/users")
            .build()
            .getObjectListSingle(User::class.java)
    }

    fun getRecipesForTag(idTag:Int): Single<List<Recipe>> {
        return Rx2AndroidNetworking.get("http://10.0.2.2/php/nesti_administration/api/recipesForTag/${idTag}")
                .build()
                .getObjectListSingle(Recipe::class.java)
    }
    fun getRecipesForPartialName(partialName:String): Single<List<Recipe>> {
        return Rx2AndroidNetworking.get("http://10.0.2.2/php/nesti_administration/api/recipesForPartialName/${partialName}")
                .build()
                .getObjectListSingle(Recipe::class.java)
    }

    fun getTags(): Single<List<Tag>> {
        return Rx2AndroidNetworking.get("http://10.0.2.2/php/nesti_administration/api/tags")
                .build()
                .getObjectListSingle(Tag::class.java)
    }
}