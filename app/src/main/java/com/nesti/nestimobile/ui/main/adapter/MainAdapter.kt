package com.nesti.nestimobile.ui.main.adapter

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.model.User
import com.nesti.nestimobile.ui.main.view.CategoryActivity
import com.nesti.nestimobile.ui.main.view.MainActivity
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.item_layout_tag.view.*
import kotlin.coroutines.coroutineContext

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

            itemView.buttonCategory.setText(textId);
            itemView.buttonCategory.setBackgroundColor(ContextCompat.getColor(itemView.context, colorId));

            itemView.buttonCategory.setOnClickListener {
                val intent = Intent(it.context, CategoryActivity::class.java);
                intent.putExtra("com.nesti.nestimobile.test", "message");
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