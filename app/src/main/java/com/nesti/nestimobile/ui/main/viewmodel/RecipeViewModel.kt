package com.nesti.nestimobile.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.repository.RecipeRepository
import com.nesti.nestimobile.data.repository.TagRepository
import com.nesti.nestimobile.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {
    //private val tags = MutableLiveData<Resource<List<Tag>>>()
    lateinit var recipe:Recipe
    // will dispose Single containing tag list when activity changes
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchTags()
    }

    private fun fetchTags() {
        /*ctags.postValue(Resource.loading(null))
        ompositeDisposable.add(
                repository.findAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ tagList ->
                            tags.postValue(Resource.success(tagList))
                        }, { throwable ->
                            throwable.printStackTrace();
                            tags.postValue(Resource.error("Something Went Wrong", null))
                        })
        )*/
    }

    // Called when VM is no longer needed (activity changed...)
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose() // dispose Single on activity change
    }

    /*fun getTags(): LiveData<Resource<List<Tag>>> {
        return tags
    }*/
}