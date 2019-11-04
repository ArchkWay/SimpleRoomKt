package com.example.simpleroomkt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.car_item.view.*

class CarAdapter internal constructor(
    context: Context,
    private val te: TouchEvent
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    interface TouchEvent {
        fun onClick(item: CarNote)
        fun onHold(item: CarNote)
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var objects = emptyList<CarNote>()

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemView = inflater.inflate(R.layout.car_item, parent, false)
        return CarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val current = objects[position]

        holder.itemView.tvYear.text = current.year
        holder.itemView.tvMark.text = current.mark
        holder.itemView.tvProducer.text = current.producer
        holder.itemView.tvClassModel.text = current.classModel
        holder.itemView.tvBody.text = current.body

        holder.itemView.setOnClickListener {
            te.onClick(current)
        }
        holder.itemView.setOnLongClickListener {
            te.onHold(current)
            return@setOnLongClickListener true
        }
    }

    internal fun setItems(items: List<CarNote>) {
        this.objects = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = objects.size
}
