package com.nesti.nestimobile.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Paragraph
import com.nesti.nestimobile.data.model.Recipe
import kotlinx.android.synthetic.main.item_layout_paragraph.view.*
import kotlinx.android.synthetic.main.item_layout_recipe.view.*

class ParagraphListAdapter(
        private val items: ArrayList<Paragraph>
        ) : RecyclerView.Adapter<ParagraphListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Paragraph) {
            itemView.textView_paragraph_content.text = item.content
            itemView.textView_paragraph_position.text = item.paragraphPosition.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout_paragraph, parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(items[position])

    fun addData(list: List<Paragraph>) {
        items.addAll(list)
    }

}