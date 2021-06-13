package com.nesti.nestimobile.ui.main.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nesti.nestimobile.lib.NestiMobileApplication
import com.nesti.nestimobile.ui.base.NestiViewModelFactory
import com.nesti.nestimobile.ui.main.viewmodel.RecipeViewModel
import com.nesti.nestimobile.ui.base.StatusContainer
import kotlinx.android.synthetic.main.activity_recipe_list.*

abstract class BaseRecipeFragment: Fragment() {
    lateinit var viewModel:RecipeViewModel
    abstract val layout:Int;

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(
                requireActivity(),
                NestiViewModelFactory(context!!.applicationContext as NestiMobileApplication)
        ).get(RecipeViewModel::class.java)
    }
    /**
     * sets up logic related to events signaled by observable objects returned by the viewmodel
     * (such as rendering a list when it's status changes to SUCCESS)
     */
    protected fun<TData> setupObserver(liveData: LiveData<StatusContainer<TData>>, successFunction: (TData) -> Unit ) {
        liveData.observe(this, Observer {
            // viewModel returns an observable Single containing a resource which can have 3
            // statuses depending on API request status
            when (it.status) {
                StatusContainer.Status.SUCCESS -> {
                    // If Resource's status property becomes "SUCCESS", we know its data property
                    // contains the fetched list of entities
                    progress_bar.visibility = View.GONE // hide loading indicator
                    // invoke successFunction on data contained in resource
                    it.data?.let { data -> successFunction.invoke(data) }
                    recycler_view.visibility = View.VISIBLE // show list
                }
                StatusContainer.Status.LOADING -> {
                    // If Resource's status becomes "LOADING", its data property is still empty
                    progress_bar.visibility = View.VISIBLE // show loading indicator
                    recycler_view.visibility = View.GONE  // hide list
                }
                StatusContainer.Status.ERROR -> {
                    // If Resource's status becomes "ERROR", such as API fetch failed
                    progress_bar.visibility = View.GONE // hide loading indicator
                    // show error message
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}