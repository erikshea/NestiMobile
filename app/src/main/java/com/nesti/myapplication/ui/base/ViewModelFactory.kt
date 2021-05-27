package com.nesti.myapplication.ui.base

import MainRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nesti.myapplication.ui.main.viewmodel.MainViewModel
import com.nesti.myapplication.utils.ApiHelper


class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}