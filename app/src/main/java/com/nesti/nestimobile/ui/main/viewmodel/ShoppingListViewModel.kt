package com.nesti.nestimobile.ui.main.viewmodel

import IngredientDao
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nesti.nestimobile.data.model.Ingredient
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.repository.TagRepository
import com.nesti.nestimobile.utils.Resource
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class ShoppingListViewModel(application: Application) : AndroidViewModel(application) {
    public val ingredients = MutableLiveData<Resource<List<Ingredient>>>()
    val context  = application
    // will dispose Single containing tag list when activity changes
    private val compositeDisposable = io.reactivex.rxjava3.disposables.CompositeDisposable()

    init {
        fetchIngredients()
    }

    public fun fetchIngredients() {
        val ingredientDao = IngredientDao(context)
        ingredients.setValue(Resource.success(ingredientDao.findAll()))
    }

    // Called when VM is no longer needed (activity changed...)
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose() // dispose Single on activity change
    }

    fun getIngredients(): LiveData<Resource<List<Ingredient>>> {
        return ingredients
    }



    fun deleteAll(){
        val ingredientDao = IngredientDao(context)

        ingredients.value?.data?.forEach {
            ingredientDao.delete(it)
        }

        ingredients.value = Resource.success(listOf());
    }
}