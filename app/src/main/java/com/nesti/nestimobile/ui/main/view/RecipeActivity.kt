package com.nesti.nestimobile.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.nesti.nestimobile.R
import com.nesti.nestimobile.ui.main.adapter.MainAdapter
import com.nesti.nestimobile.data.api.ApiService
import com.nesti.nestimobile.lib.NestiMobileApplication
import com.nesti.nestimobile.ui.base.ViewModelFactory
import com.nesti.nestimobile.ui.main.adapter.RecipeStateAdapter
import com.nesti.nestimobile.ui.main.viewmodel.RecipeViewModel
import com.nesti.nestimobile.utils.ApiHelper

class RecipeActivity : BaseActivity<RecipeViewModel>() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        tabLayout.addTab(tabLayout.newTab().setText(R.string.recipe_tab_title_ingredients))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.recipe_tab_title_steps))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = RecipeStateAdapter(this, supportFragmentManager,
                tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        title = viewModel.recipe.name;
    }

    override fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiService((applicationContext as NestiMobileApplication).configuration)))
        ).get(RecipeViewModel::class.java)

        viewModel.recipe = intent.getParcelableExtra("com.nesti.nestimobile.recipe")!!;
    }

    override fun setupUI() {
    }

    fun fabShoppingListClicked(view: View) {
        val intent = Intent(view.context, ShoppingListActivity::class.java);
        ContextCompat.startActivity(view.context, intent, Bundle.EMPTY);
    }
}