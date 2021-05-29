package com.nesti.nestimobile.data.api

import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.model.User
import io.reactivex.Single

interface ApiService {
    fun getUsers(): Single<List<User>>;
    fun getRecipes(): Single<List<Recipe>>;
    fun getTags(): Single<List<Tag>>;
}