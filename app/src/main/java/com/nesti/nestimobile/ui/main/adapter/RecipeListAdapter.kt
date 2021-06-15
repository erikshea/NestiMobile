package com.nesti.nestimobile.ui.main.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.ui.main.adapter.base.BaseDataViewHolder
import com.nesti.nestimobile.ui.main.adapter.base.BaseRecyclerViewAdapter
import com.nesti.nestimobile.ui.main.view.RecipeActivity
import kotlinx.android.synthetic.main.item_recipe.view.*

/**
 * adapter for a recyclerview that shows a category's list of recipes
 * @param recipes list of Recipe entities
 */
class RecipeListAdapter( private val recipes: ArrayList<Recipe> )
    : BaseRecyclerViewAdapter<Recipe, RecipeListAdapter.DataViewHolder>(recipes) {
    /**
     * sets up views for recyclerview lines
     * @param itemView view object for line
     */
    class DataViewHolder(itemView: View) : BaseDataViewHolder<Recipe>(itemView) {
        override fun bind(recipe: Recipe) {
            itemView.textView_recipe_title.text = recipe.name
            itemView.textView_recipe_author.text = recipe.author

            // star rating resource name is in the form "star_5"
            val difficultyImageId = resources.getIdentifier(
                "star_" + recipe.difficulty,
                "drawable", packageName )
            itemView.imageView_recipe_difficulty.setImageResource(difficultyImageId)

            // fetch image from url given in API response
            Glide.with(itemView.imageView_recipe_image.context)
                .load(recipe.imageUrl)
                .into(itemView.imageView_recipe_image)

            itemView.setOnClickListener {
                // clicking on a recipe shows detail tabs
                val intent = Intent(it.context, RecipeActivity::class.java);
                intent.putExtra("com.nesti.nestimobile.recipe", recipe);
                ContextCompat.startActivity(it.context, intent, Bundle.EMPTY);
            }
        }
    }

    /**
     * sets up a view holder which will bind values to the recyclerview lines
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_recipe, parent,
                        false
                )
        )
}