package com.nesti.nestimobile.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Recipe
import kotlinx.android.synthetic.main.item_layout_recipe.view.*

class IngredientListAdapter(
        private val recipes: ArrayList<Recipe>
        ) : RecyclerView.Adapter<IngredientListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: Recipe) {
            itemView.textView_recipe_title.text = recipe.name
            itemView.textView_recipe_title.text = recipe.name
            //itemView.textView_recipe_image.text = recipe.name
            Glide.with(itemView.imageView_recipe_image.context)
                .load(recipe.image)
                .into(itemView.imageView_recipe_image)
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