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

/**
 * adapter for a recyclerview that shows a recipe's list of steps
 * @param items list of Paragraph entities
 */
class ParagraphListAdapter(
        private val items: ArrayList<Paragraph>
        ) : RecyclerView.Adapter<ParagraphListAdapter.DataViewHolder>() {

    /**
     * sets up views for recyclerview lines
     * @param itemView view object for line
     */
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * binds data to an individual recyclerview line
         */
        fun bind(item: Paragraph) {
            itemView.textView_paragraph_content.text = item.content
            itemView.textView_paragraph_position.text = item.paragraphPosition.toString()
        }
    }

    /**
     * sets up a  view holder which will bind values to the recyclerview lines
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout_paragraph, parent,
                false
            )
        )

    /**
     * Determines recyclerview size
     */
    override fun getItemCount(): Int = items.size

    /**
     * Will call our view holder's bind method when a line is displayed
     */
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(items[position])

    /**
     * Sets up the list of entities from which the recyclerView is built
     */
    fun addData(list: List<Paragraph>) {
        items.addAll(list)
    }

}