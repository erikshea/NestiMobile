package com.nesti.nestimobile.ui.main.adapter
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nesti.nestimobile.ui.main.view.RecipeActivity
import com.nesti.nestimobile.ui.main.view.RecipeIngredientsFragment
import com.nesti.nestimobile.ui.main.view.RecipeStepsFragment

@Suppress("DEPRECATION")
internal class RecipeStateAdapter(
        var context: Context,
        fm: FragmentManager,
        var totalTabs: Int
) :
        FragmentPagerAdapter(fm) {
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
    override fun getCount(): Int {
        return totalTabs
    }
}