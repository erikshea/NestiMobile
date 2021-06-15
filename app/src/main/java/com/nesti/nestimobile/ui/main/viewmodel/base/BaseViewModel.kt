package com.nesti.nestimobile.ui.main.viewmodel.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nesti.nestimobile.ui.base.StatusContainer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel: ViewModel() {
    // will dispose observables when activity changes
    private val compositeDisposable = CompositeDisposable()

    /**
     * when an observable object has its value set, sends the new value to a mutable LiveData object
     * containing a StatusContainer (SUCCESS, ERROR, LOADING...) that the view can exploit.
     * @param observable non-mutable LiveData object containing an observed regular object
     *      example: Single<List<Recipe>>
     * @param liveData mutable LiveData object containing a StatusContainer exploitable by the view
     *      example: MutableLiveData<StatusContainer<List<Recipe>>>
     * @param TObserved type of observed object, for example List<Recipe>
     *      example: List<Recipe>>
     */
    protected fun<TObserved> sendToMutableLiveDataWhenInitialized(
        observable: Single<TObserved> ,
        liveData: MutableLiveData<StatusContainer<TObserved>>
    )
    {

        liveData.postValue(StatusContainer.loading())
        compositeDisposable.add(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // need to observe on UI thread
                .subscribe(
                    // On success, send liveData a StatusContainer containing the new valid data
                    { observed -> liveData.postValue(StatusContainer.success(observed)) },
                    // On error, send liveData a StatusContainer with a message and an ERROR status
                    { _ -> liveData.postValue(StatusContainer.error("SThe server is unreachable."))
                })
        )
    }

    /**
     * Called when VM is no longer needed (activity changed...)
     */
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose() // dispose observables on activity end of life
    }
}