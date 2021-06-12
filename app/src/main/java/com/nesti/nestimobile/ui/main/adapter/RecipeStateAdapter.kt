package com.nesti.nestimobile.ui.main.adapter
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nesti.nestimobile.ui.main.view.RecipeActivity
import com.nesti.nestimobile.ui.main.view.RecipeIngredientsFragment
import com.nesti.nestimobile.ui.main.view.RecipeStepsFragment

/**
 * Adapter for tab pager
 * @param context activity context
 * @param fm fragment manager
 * @param totalTabs number of tabs
 */
@Suppress("DEPRECATION")
internal class RecipeStateAdapter(
        var context: Context,
        fm: FragmentManager,
        var totalTabs: Int
) : FragmentPagerAdapter(fm) {
    /**
     * returns fragment to use depending on index of clicked tab
     * @param position tab index
     */
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                RecipeIngredientsFragment()
            }
            1 -> {
                RecipeStepsFragment()
            }
            else -> getItem(position)
        }
    }

    /**
     * returns number of tabs
     */
    override fun getCount(): Int {
        return totalTabs
    }
}