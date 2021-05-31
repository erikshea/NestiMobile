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
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ShoppingListViewModel() : AndroidViewModel() {
    private val ingredients = MutableLiveData<Resource<List<Ingredient>>>()

    // will dispose Single containing tag list when activity changes
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchIngredients()
    }

    private fun fetchIngredients() {
        val ingredientDao = IngredientDao(getApplication<Application>().applicationContext)
        val ingredients = ingredientDao.findAll()
        val ingredientsSingle: Single<List<Ingredient>>

        ingredients.postValue(Resource.loading(null))
        compositeDisposable.add(
            ingredientDao.findAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ list ->
                            ingredients.postValue(Resource.success(list))
                        }, { throwable ->
                            throwable.printStackTrace();
                            ingredients.postValue(Resource.error("Something Went Wrong", null))
                        })
        )
    }

    // Called when VM is no longer needed (activity changed...)
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose() // dispose Single on activity change
    }

    fun getTags(): LiveData<Resource<List<Ingredient>>> {
        return ingredients
    }
}