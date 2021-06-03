package com.nesti.nestimobile.ui.main.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.nesti.nestimobile.R
import com.nesti.nestimobile.lib.ApplicationConfiguration
import com.nesti.nestimobile.lib.NestiMobileApplication

abstract class BaseActivity<TViewModel : ViewModel>: AppCompatActivity() {
    protected lateinit var viewModel: TViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupViewModel()
    }

    protected abstract fun setupUI()


    protected abstract fun setupViewModel()

    override fun onCreateOptionsMenu(pMenu: Menu): Boolean {
        val inflater = this.menuInflater
        inflater.inflate(R.menu.menu_general, pMenu)

        return true
    }


    override fun onOptionsItemSelected(pItem: MenuItem): Boolean {
        when (pItem.itemId) {
            R.id.menu_search -> {
                onSearchRequested();
            }

            R.id.menu_list -> {

            }
        }
        return true
    }

}