package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.api.ApiService
import com.nesti.nestimobile.ui.base.ViewModelFactory
import com.nesti.nestimobile.ui.main.viewmodel.RecipeViewModel
import com.nesti.nestimobile.utils.ApiHelper

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
                ViewModelFactory(ApiHelper(ApiService()))
        ).get(RecipeViewModel::class.java)
    }
}