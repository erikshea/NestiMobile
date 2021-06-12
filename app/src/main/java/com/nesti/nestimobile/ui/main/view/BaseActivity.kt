package com.nesti.nestimobile.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.nesti.nestimobile.R

/**
 * Base class shared by other activities
 * @param TViewModel viewmodel class on which bindings will be made
 */
abstract class BaseActivity<TViewModel : ViewModel>: AppCompatActivity() {
    protected lateinit var viewModel: TViewModel

    /**
     * Additionally calls implemented setupUi and setupViewModel methods
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
        setupViewModel()
    }

    /**
     * Initializes adapters and passes them to view objects.
     */
    protected abstract fun setupUi()

    /**
     * Initializes viewmodel from class type parameter
     */
    protected abstract fun setupViewModel()

    /**
     * Defines shared menu containing search, shopping list, contacts...
     * @param menu menu object
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = this.menuInflater
        inflater.inflate(R.menu.menu_general, menu)
        return true
    }

    /**
     * Defines click handlers for menu items
     */
    override fun onOptionsItemSelected(pItem: MenuItem): Boolean {
        when (pItem.itemId) {
            R.id.menu_search -> {
                // calls "default_searchable" search widget as defined in manifest
                onSearchRequested();
            }

            R.id.menu_list -> {
                // shows shopping list
                ContextCompat.startActivity(
                    this,
                    Intent(applicationContext, ShoppingListActivity::class.java),
                    Bundle.EMPTY
                );
            }

            R.id.menu_contact -> {
                // shows contact section
                ContextCompat.startActivity(
                    this,
                    Intent(applicationContext, ContactActivity::class.java),
                    Bundle.EMPTY
                );
            }

            R.id.menu_project -> {
                // shows project section
                ContextCompat.startActivity(
                    this,
                    Intent(applicationContext, ProjectActivity::class.java),
                    Bundle.EMPTY
                );
            }

            R.id.menu_team -> {
                // shows team members section
                ContextCompat.startActivity(
                    this,
                    Intent(applicationContext, TeamActivity::class.java),
                    Bundle.EMPTY
                );
            }
        }
        return true
    }

}