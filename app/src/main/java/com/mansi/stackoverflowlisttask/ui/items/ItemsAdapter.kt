package com.mansi.stackoverflowlisttask.ui.items

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.chip.Chip
import com.mansi.stackoverflowlisttask.data.entities.Items
import com.mansi.stackoverflowlisttask.databinding.ItemListBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ItemsAdapter(private val listener: ItemsItemListener) : RecyclerView.Adapter<ItemViewHolder>() {

    interface ItemsItemListener {
        fun onClickedItem(ItemId: String)
    }

    private val items = ArrayList<Items>()

    fun setItems(items: MutableList<Items>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: ItemListBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding, listener)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = run {
        holder.bind(items[position])
    }
}

    class ItemViewHolder(
            private val itemBinding: ItemListBinding,
            private val listener: ItemsAdapter.ItemsItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root),View.OnClickListener {

    private lateinit var item: Items

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Items) {
        this.item = item
        itemBinding.title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
        itemBinding.timeText.text = convertLongToTime(item.creationDate*1000)
        Glide.with(itemBinding.root)
            .load(item.owner.profileImage)
            .transform(CircleCrop())
            .into(itemBinding.profileImage)
        itemBinding.tagsText.removeAllViews()
        for (i in item.tags){
            val chip = Chip(itemBinding.tagsText.context)
            chip.text = i
            // Make the chip clickable
            chip.isClickable = false
            chip.isCheckable = false
            chip.isCloseIconVisible = false
            // Finally, add the chip to chip group
            itemBinding.tagsText.addView(chip)
        }
    }


    override fun onClick(v: View?) {
        listener.onClickedItem(item.link)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("hh:mm aa")
        return format.format(date)
    }
}

