package com.nesti.nestimobile.ui.main.viewmodel

import IngredientDao
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nesti.nestimobile.data.model.Ingredient
import com.nesti.nestimobile.ui.base.StatusContainer
import com.nesti.nestimobile.ui.main.viewmodel.base.BaseViewModel

/**
 * viewmodel for shopping list showing a list of ingredients
 */
class ShoppingListViewModel(private val application: Application) : BaseViewModel() {
    val ingredients = MutableLiveData<StatusContainer<List<Ingredient>>>()

    /**
     * get view-ready observable ingredients
     */
    fun getIngredients(): LiveData<StatusContainer<List<Ingredient>>> {
        if ( ingredients.value == null ){
            val ingredientDao = IngredientDao(application)
            ingredients.value = StatusContainer.success(ingredientDao.findAll())
        }
        return ingredients
    }

    /**
     * clears ingredient table, notifies view of change
     */
    fun deleteAll(){
        val ingredientDao = IngredientDao(application)

        ingredients.value?.data?.forEach {
            ingredientDao.delete(it)
        }

        // send empty list to view
        ingredients.value = StatusContainer.success(listOf());
    }
}