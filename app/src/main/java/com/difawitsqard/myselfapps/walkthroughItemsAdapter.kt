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

class walkthroughItemsAdapter(private val walkthroughItems: List<walkthroughItem>) :
    RecyclerView.Adapter<walkthroughItemsAdapter.walkthroughItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): walkthroughItemViewHolder {
        return walkthroughItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.walkthrough_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: walkthroughItemViewHolder, position: Int) {
        holder.bind(walkthroughItems[position])
    }

    override fun getItemCount(): Int {
        return walkthroughItems.size
    }

    inner class walkthroughItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageWalkthrough = view.findViewById<ImageView>(R.id.imageWalkthrough)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)

        fun bind(walkthroughItem: walkthroughItem) {
            imageWalkthrough.setImageResource(walkthroughItem.walkthroughImage)
            textTitle.text = walkthroughItem.title
            textDescription.text = walkthroughItem.description
        }

    }
}