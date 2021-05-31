package com.nesti.nestimobile.ui.main.adapter

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.ui.main.view.RecipeActivity
import kotlinx.android.synthetic.main.item_layout_recipe.view.*




class RecipeListAdapter(
        private val recipes: ArrayList<Recipe>
) : RecyclerView.Adapter<RecipeListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: Recipe) {
            itemView.textView_recipe_title.text = recipe.name
            itemView.textView_recipe_author.text = recipe.author

            val difficultyImageId = itemView.context.resources.getIdentifier("star_" + recipe.difficulty, "drawable", itemView.context.packageName)
            itemView.imageView_recipe_difficulty.setImageResource(difficultyImageId)

            Glide.with(itemView.imageView_recipe_image.context)
                .load(recipe.image)
                .into(itemView.imageView_recipe_image)

            itemView.setOnClickListener {
                val intent = Intent(it.context, RecipeActivity::class.java);
                intent.putExtra("com.nesti.nestimobile.recipe", recipe);
                ContextCompat.startActivity(it.context, intent, Bundle.EMPTY);
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_layout_recipe, parent,
                        false
                )
        )

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(recipes[position])

    fun addData(list: List<Recipe>) {
        recipes.addAll(list)
    }

}