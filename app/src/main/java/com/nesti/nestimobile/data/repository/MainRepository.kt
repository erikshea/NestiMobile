package com.nesti.nestimobile.data.repository
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.model.User
import com.nesti.nestimobile.utils.ApiHelper
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }
    fun getRecipes(): Single<List<Recipe>> {
        return apiHelper.getRecipes()
    }

    fun getTags(): Single<List<Tag>> {
        return apiHelper.getTags()
    }
}