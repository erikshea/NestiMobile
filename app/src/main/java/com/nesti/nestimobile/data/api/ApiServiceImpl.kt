package com.nesti.nestimobile.data.api

import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.model.User
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getUsers(): Single<List<User>> {
        return Rx2AndroidNetworking.get("https://5e510330f2c0d300147c034c.mockapi.io/users")
            .build()
            .getObjectListSingle(User::class.java)
    }

    override fun getRecipes(): Single<List<Recipe>> {
        return Rx2AndroidNetworking.get("http://10.0.2.2/php/codeigniter/CodeIgniterTP/public/index.php/api/category/glutenfree")
                .build()
                .getObjectListSingle(Recipe::class.java)
    }


    override fun getTags(): Single<List<Tag>> {
        return Rx2AndroidNetworking.get("http://10.0.2.2/php/nesti_administration/api/tagsApi")
                .build()
                .getObjectListSingle(Tag::class.java)
    }
}