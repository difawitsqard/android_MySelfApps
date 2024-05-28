package com.difawitsqard.myselfapps

// Created : 26/05/24
// NIM     : 10121916
// Nama    : Difa Witsqa RD
// Kelas   : IF9K

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DailyActivityItemsAdapter(private val dailyActivityList: List<DailyActivityItem>) :
    RecyclerView.Adapter<DailyActivityItemsAdapter.DailyActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyActivityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.daily_activity_item_container, parent, false)
        return DailyActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyActivityViewHolder, position: Int) {
        val dailyActivity = dailyActivityList[position]
        Picasso.get().load(dailyActivity.imageResource).into(holder.imageView)
        holder.textView.text = dailyActivity.description
    }

    override fun getItemCount(): Int = dailyActivityList.size

    class DailyActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewDailyActivity)
        val textView: TextView = itemView.findViewById(R.id.textViewDailyActivity)
    }
}
