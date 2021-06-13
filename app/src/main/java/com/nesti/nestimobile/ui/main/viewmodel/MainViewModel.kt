package com.nesti.nestimobile.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.repository.TagRepository
import com.nesti.nestimobile.ui.base.StatusContainer
import com.nesti.nestimobile.ui.main.viewmodel.base.BaseViewModel

/**
 * viewmodel for main splash screen showing list of categories
 */
class MainViewModel(private val tagRepository: TagRepository) : BaseViewModel() {
    private val tags = MutableLiveData<StatusContainer<List<Tag>>>()

    /**
     * get view-ready observable tags
     */
    fun getTags(): LiveData<StatusContainer<List<Tag>>> {
        if ( tags.value == null ){
            sendToMutableLiveDataWhenInitialized(tagRepository.findAll(), tags)
        }
        return tags
    }
}