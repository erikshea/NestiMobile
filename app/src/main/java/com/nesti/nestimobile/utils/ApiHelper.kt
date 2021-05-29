package com.nesti.nestimobile.utils

import com.nesti.nestimobile.data.api.ApiService

class ApiHelper(private val apiService: ApiService) {

    fun getUsers() = apiService.getUsers();
    fun getRecipes() = apiService.getRecipes();
    fun getTags() = apiService.getTags();

}