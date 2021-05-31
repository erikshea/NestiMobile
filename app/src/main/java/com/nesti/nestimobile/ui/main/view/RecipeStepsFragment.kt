package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.api.ApiService
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.ui.base.ViewModelFactory
import com.nesti.nestimobile.ui.main.viewmodel.RecipeViewModel
import com.nesti.nestimobile.utils.ApiHelper


class RecipeStepsFragment() : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var viewModel = ViewModelProviders.of(
                requireActivity(),
                ViewModelFactory(ApiHelper(ApiService()))
        ).get(RecipeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_recipe_steps, container, false)
    }
}