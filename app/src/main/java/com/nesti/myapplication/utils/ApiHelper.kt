package com.nesti.myapplication.utils

import com.nesti.myapplication.data.api.ApiService

class ApiHelper(private val apiService: ApiService) {

    fun getUsers() = apiService.getUsers()

}