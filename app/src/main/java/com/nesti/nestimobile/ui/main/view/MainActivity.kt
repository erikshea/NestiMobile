package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesti.nestimobile.R
import com.nesti.nestimobile.lib.NestiMobileApplication
import com.nesti.nestimobile.ui.base.NestiViewModelFactory
import com.nesti.nestimobile.ui.main.adapter.MainAdapter
import com.nesti.nestimobile.ui.main.view.base.BaseActivity
import com.nesti.nestimobile.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * application splash screen shows a list of categories
 */
class MainActivity : BaseActivity() {
    private lateinit var adapter: MainAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            NestiViewModelFactory(applicationContext as NestiMobileApplication)
        ).get(MainViewModel::class.java)

        setupObserver(viewModel.getTags()) { tags -> adapter.addData(tags) }
    }

    override fun setupUi() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recycler_view.adapter = adapter
    }
}