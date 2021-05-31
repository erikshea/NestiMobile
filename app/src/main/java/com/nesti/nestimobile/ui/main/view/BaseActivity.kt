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

abstract class BaseActivity<TViewModel : ViewModel>: AppCompatActivity() {
    protected lateinit var viewModel: TViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        setupUI()
        setupViewModel()
    }

    protected abstract fun setupUI()


    protected abstract fun setupViewModel()

    override fun onCreateOptionsMenu(pMenu: Menu): Boolean {
        val inflater = this.menuInflater
        inflater.inflate(R.menu.menu_general, pMenu)
        /*var t = pMenu.findItem(R.id.menu_search)
        var t2 = pMenu.findItem(R.id.menu_search).actionView
        var t3  = R.id.menu_list
        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (pMenu.findItem(R.id.menu_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
        }*/

        return true
    }


    override fun onOptionsItemSelected(pItem: MenuItem): Boolean {
        when (pItem.itemId) {
            R.id.menu_search -> {
                onSearchRequested();
            }

            R.id.menu_list -> {

            }
            R.id.menu_contact -> {
                //val intent = Intent(this, TabRecipeActivity::class.java)
                //tartActivity(intent)
            }
        }
        return true
    }
}