package com.nesti.nestimobile.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nesti.nestimobile.data.base.NestiDataSource
import com.nesti.nestimobile.data.datasource.NestiApiDataSource
import com.nesti.nestimobile.data.repository.RecipeRepository
import com.nesti.nestimobile.data.repository.TagRepository
import com.nesti.nestimobile.lib.NestiMobileApplication
import com.nesti.nestimobile.ui.main.viewmodel.*

/**
 * View model providers create a viewmodel that lasts for the lifetime of the activity, and this
 * subclass additionally passes a data source to the VMs
 * @param applicationContext application context for configuration
 */
class NestiViewModelFactory(private val applicationContext: NestiMobileApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // Data source is Nesti API
        val dataSource = NestiApiDataSource(applicationContext.configuration)
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(TagRepository(dataSource))
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> CategoryViewModel(RecipeRepository(dataSource))
            modelClass.isAssignableFrom(SearchResultsViewModel::class.java) -> SearchResultsViewModel(RecipeRepository(dataSource))
            modelClass.isAssignableFrom(RecipeViewModel::class.java) -> RecipeViewModel(RecipeRepository(dataSource))
            else -> throw IllegalArgumentException("Viewmodel class $modelClass not defined in factory")
        } as T
    }

}