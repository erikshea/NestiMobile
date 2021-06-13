package com.nesti.nestimobile.ui.main.view.base

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nesti.nestimobile.R
import com.nesti.nestimobile.ui.base.StatusContainer
import com.nesti.nestimobile.ui.main.view.ContactActivity
import com.nesti.nestimobile.ui.main.view.ProjectActivity
import com.nesti.nestimobile.ui.main.view.ShoppingListActivity
import com.nesti.nestimobile.ui.main.view.TeamActivity
import kotlinx.android.synthetic.main.activity_recipe_list.*

/**
 * Base class shared by other activities
 */
abstract class BaseActivity: AppCompatActivity() {

    /**
     * Additionally calls implemented setupUi and setupViewModel methods
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
    }

    /**
     * Initializes adapters and passes them to view objects.
     */
    protected abstract fun setupUi()

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

    /**
     * sets up logic related to events signaled by observable objects (such as rendering a list
     * when it's status changes to SUCCESS)
     * @param liveData observable containing a Resource
     * @param successFunction function executed when Resource status changed to SUCCESS
     * @param TData type of object wrapped in Resource
     */
    protected fun<TData> setupObserver(
        liveData: LiveData<StatusContainer<TData>>, // observable containing a Resource
        successFunction: (TData) -> Unit // function exec
    ) {
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
                    // If Resource's status becomes "ERROR" such as API fetch failed
                    progress_bar.visibility = View.GONE // hide loading indicator
                    // show error message
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


}