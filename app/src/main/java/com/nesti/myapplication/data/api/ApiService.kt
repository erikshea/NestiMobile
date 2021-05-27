package com.nesti.myapplication.data.api

import com.nesti.myapplication.data.model.User
import io.reactivex.Single

interface ApiService {
    fun getUsers(): Single<List<User>>;
}