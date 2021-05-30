package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(pMenu: Menu?): Boolean {
        val inflater = this.menuInflater
        inflater.inflate(R.menu.menu_general, pMenu)
        return true
    }

    override fun onOptionsItemSelected(pItem: MenuItem): Boolean {
        when (pItem.itemId) {
            R.id.menu_search -> {
                //val intent = Intent(this, TabRecipeActivity::class.java)
                startActivity(intent)
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