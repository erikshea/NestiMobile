package com.nesti.nestimobile.ui.main.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.ui.main.view.CategoryActivity
import kotlinx.android.synthetic.main.item_layout_tag.view.*

class MainAdapter(
        private val tags: ArrayList<Tag>
        ) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val res = itemView.context.resources
        private val packageName = itemView.context.packageName

        fun bind(tag: Tag) {
            val resourceName = "category_${tag.name}";
            // button background color and text are named "category_<tag name>"
            val textId: Int = res.getIdentifier(resourceName, "string", packageName)
            val colorId: Int = res.getIdentifier(resourceName, "color", packageName)

            itemView.button_category.setText(textId);
            itemView.button_category.setBackgroundColor(ContextCompat.getColor(itemView.context, colorId));

            itemView.button_category.setOnClickListener {
                val intent = Intent(it.context, CategoryActivity::class.java);
                intent.putExtra("com.nesti.nestimobile.tag", tag);
                intent.putExtra("com.nesti.nestimobile.title",  res.getString(textId));
                startActivity(it.context,intent, Bundle.EMPTY);
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            DataViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_layout_tag, parent,
                            false
                    )
            )

    override fun getItemCount(): Int = tags.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
            holder.bind(tags[position])

    fun addData(list: List<Tag>) {
        tags.addAll(list)
    }
}