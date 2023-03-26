package com.tthieu.myhelsinki.common.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tthieu.myhelsinki.common.presentation.model.UIEvent
import com.tthieu.myhelsinki.common.utils.setImage
import com.tthieu.myhelsinki.databinding.RecyclerViewEventItemBinding

class EventsAdapter: ListAdapter<UIEvent, EventsAdapter.EventsViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val binding = RecyclerViewEventItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return EventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val item: UIEvent = getItem(position)

        holder.bind(item)
    }

    inner class EventsViewHolder(
        private val binding: RecyclerViewEventItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UIEvent) {
            binding.name.text = item.title
            binding.date.text = item.date
            binding.intro.text = item.intro
            binding.photo.setImage(item.photo)
        }
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<UIEvent>() {
    override fun areItemsTheSame(oldItem: UIEvent, newItem: UIEvent): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UIEvent, newItem: UIEvent): Boolean {
        return oldItem == newItem
    }
}