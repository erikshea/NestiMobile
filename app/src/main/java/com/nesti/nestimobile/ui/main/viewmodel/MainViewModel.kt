package com.nesti.nestimobile.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.model.User
import com.nesti.nestimobile.data.repository.MainRepository
import com.nesti.nestimobile.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val tags = MutableLiveData<Resource<List<Tag>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchTags()
    }

    private fun fetchTags() {
        tags.postValue(Resource.loading(null))
        compositeDisposable.add(
                mainRepository.getTags()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ tagList ->
                            tags.postValue(Resource.success(tagList))
                        }, { throwable ->
                            throwable.printStackTrace();
                            tags.postValue(Resource.error("Something Went Wrong", null))
                        })
        )
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getTags(): LiveData<Resource<List<Tag>>> {
        return tags
    }
}