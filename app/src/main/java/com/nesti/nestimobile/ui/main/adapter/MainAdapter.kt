package com.nesti.nestimobile.ui.main.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.ui.main.adapter.base.BaseDataViewHolder
import com.nesti.nestimobile.ui.main.adapter.base.BaseRecyclerViewAdapter
import com.nesti.nestimobile.ui.main.view.CategoryActivity
import kotlinx.android.synthetic.main.item_tag.view.*

/**
 * adapter for a recyclerview that shows a list of categories (tags)
 * @param tags list of Tag entities
 */
class MainAdapter(private val tags: ArrayList<Tag>)
    : BaseRecyclerViewAdapter<Tag, MainAdapter.DataViewHolder>(tags) {
    /**
     * sets up views for recyclerview lines
     * @param itemView view object for line
     */
    class DataViewHolder(itemView: View) : BaseDataViewHolder<Tag>(itemView) {
        override fun bind(tag: Tag) {
            val resourceName = "category_${tag.name}";
            // button background color and text are named "category_<tag name>"
            val textId: Int = resources.getIdentifier(resourceName, "string", packageName)
            val colorId: Int = resources.getIdentifier(resourceName, "color", packageName)

            itemView.button_category.setText(textId);
            itemView.button_category.setBackgroundColor(ContextCompat.getColor(itemView.context, colorId));

            itemView.button_category.setOnClickListener {
                val intent = Intent(it.context, CategoryActivity::class.java);
                intent.putExtra("com.nesti.nestimobile.category.tag", tag);
                intent.putExtra("com.nesti.nestimobile.category.title",  resources.getString(textId));
                startActivity(it.context,intent, Bundle.EMPTY);
            }
        }
    }

    /**
     * sets up a  view holder which will bind values to the recyclerview lines
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_tag, parent,
                        false
                )
        )
}