package com.nesti.nestimobile.data.repository
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.model.User
import com.nesti.nestimobile.utils.ApiHelper
import io.reactivex.Single

class TagRepository(private val apiHelper: ApiHelper) {
    fun findAll(): Single<List<Tag>> {
        return apiHelper.getTags()
    }
}