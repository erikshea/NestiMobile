package com.nesti.nestimobile.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.User
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.item_layout_recipe.view.*

class CategoryAdapter(
        private val recipes: ArrayList<Recipe>
        ) : RecyclerView.Adapter<CategoryAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: Recipe) {
            itemView.textView_recipe_title.text = recipe.name
//            Glide.with(itemView.imageViewAvatar.context)
//                .load(user.avatar)
//                .into(itemView.imageViewAvatar)
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