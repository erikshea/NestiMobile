package com.nesti.nestimobile.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Paragraph
import com.nesti.nestimobile.ui.main.adapter.base.BaseDataViewHolder
import com.nesti.nestimobile.ui.main.adapter.base.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_paragraph.view.*

/**
 * adapter for a recyclerview that shows a recipe's list of steps
 * @param items list of Paragraph entities
 */
class ParagraphListAdapter( private val items: ArrayList<Paragraph> )
    : BaseRecyclerViewAdapter<Paragraph, ParagraphListAdapter.DataViewHolder>(items){

    /**
     * sets up views for recyclerview lines
     * @param itemView view object for line
     */
    class DataViewHolder(itemView: View) : BaseDataViewHolder<Paragraph>(itemView) {
        /**
         * binds data to an individual recyclerview line
         */
        override fun bind(item: Paragraph) {
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
                R.layout.item_paragraph, parent,
                false
            )
        )
}