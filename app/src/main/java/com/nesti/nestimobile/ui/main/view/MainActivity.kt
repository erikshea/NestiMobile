package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.lib.NestiMobileApplication
import com.nesti.nestimobile.ui.base.NestiViewModelFactory
import com.nesti.nestimobile.ui.main.adapter.MainAdapter
import com.nesti.nestimobile.ui.main.viewmodel.MainViewModel
import com.nesti.nestimobile.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

/**
 * application splash screen shows a list of categories
 */
class MainActivity : BaseActivity<MainViewModel>() {
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setupObserver()
    }

    override fun setupUi() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recycler_view.adapter = adapter
    }

    /**
     * sets up logic related to events signaled by observable objects returned by the viewmodel
     * (such as rendering a list when it's status changes to SUCCESS)
     */
    private fun setupObserver() {
        viewModel.getTags().observe(this, Observer {
            // viewModel returns an observable Single containing a resource which can have 3
            // statuses depending on API request status
            when (it.status) {
                Status.SUCCESS -> {
                    // If Resource's status property becomes "SUCCESS", we know its data property
                    // contains the fetched list of entities
                    progress_bar.visibility = View.GONE // hide loading indicator
                    it.data?.let { tags -> renderList(tags) } // call renderList with fetched data
                    recycler_view.visibility = View.VISIBLE // show list
                }
                Status.LOADING -> {
                    // If Resource's status becomes "LOADING", its data property is still empty
                    progress_bar.visibility = View.VISIBLE // show loading indicator
                    recycler_view.visibility = View.GONE // hide list
                }
                Status.ERROR -> {
                    // If Resource's status becomes "ERROR", API fetch failed
                    progress_bar.visibility = View.GONE // hide loading indicator
                    // show error message
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    /**
     * passes fetched data to this activity's RecyclerView adapter
     */
    private fun renderList(tags: List<Tag>) {
        adapter.addData(tags)
        adapter.notifyDataSetChanged() // refresh UI
    }

    override fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                NestiViewModelFactory(applicationContext as NestiMobileApplication)
        ).get(MainViewModel::class.java)
    }
}