package com.nesti.nestimobile.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.nesti.nestimobile.R
import com.nesti.nestimobile.ui.main.adapter.MainAdapter
import com.nesti.nestimobile.lib.NestiMobileApplication
import com.nesti.nestimobile.ui.base.NestiViewModelFactory
import com.nesti.nestimobile.ui.main.adapter.RecipeStateAdapter
import com.nesti.nestimobile.ui.main.view.base.BaseActivity
import com.nesti.nestimobile.ui.main.viewmodel.RecipeViewModel

/**
 * recipe details with two tabs showing ingredients and steps
 */
class RecipeActivity : BaseActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var viewModel: RecipeViewModel

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setContentView(R.layout.activity_recipe)

        // set up tab layout and tab pager
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        // add two tabs
        tabLayout.addTab(tabLayout.newTab().setText(R.string.recipe_tab_title_ingredients))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.recipe_tab_title_steps))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        // initialize adapter for tab view pager, which will handle tab switching logic
        val adapter = RecipeStateAdapter(this, supportFragmentManager,
                tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        // define tab selection logic
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // activity title is current recipe's name
        title = viewModel.recipe.name;
    }

    /**
     * Initializes viewmodel
     */
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            NestiViewModelFactory(applicationContext as NestiMobileApplication)
        ).get(RecipeViewModel::class.java)

        // pass current recipe to viewmodel
        viewModel.recipe = intent.getParcelableExtra("com.nesti.nestimobile.recipe")!!;
    }

    override fun setupUi() {
    }

    /**
     * called when shopping list button is clicked
     */
    fun fabShoppingListClicked(view: View) {
        // start shopping list activity
        val intent = Intent(view.context, ShoppingListActivity::class.java);
        ContextCompat.startActivity(view.context, intent, Bundle.EMPTY);
    }
}