package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.api.ApiService
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.lib.NestiMobileApplication
import com.nesti.nestimobile.ui.base.ViewModelFactory
import com.nesti.nestimobile.ui.main.adapter.MainAdapter
import com.nesti.nestimobile.ui.main.viewmodel.MainViewModel
import com.nesti.nestimobile.utils.ApiHelper
import com.nesti.nestimobile.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setupObserver()
    }

    override fun setupUI() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recycler_view.addItemDecoration(
                DividerItemDecoration(
                        recycler_view.context,
                        (recycler_view.layoutManager as LinearLayoutManager).orientation
                )
        )
        recycler_view.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getTags().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    it.data?.let { tags -> renderList(tags) }
                    recycler_view.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(tags: List<Tag>) {
        adapter.addData(tags)
        adapter.notifyDataSetChanged()
    }

    override fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(ApiService((applicationContext as NestiMobileApplication).configuration)))
        ).get(MainViewModel::class.java)
    }
}