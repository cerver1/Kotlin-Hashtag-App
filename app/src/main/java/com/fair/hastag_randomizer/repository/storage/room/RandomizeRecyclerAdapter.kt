package com.fair.hastag_randomizer.repository.storage.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.view.HashTagViewModel
import kotlinx.android.synthetic.main.recycler_storage.view.*

class RandomizeRecyclerAdapter(var hashtag: ArrayList<RandomizeEntity>, private val viewModel: HashTagViewModel): RecyclerView.Adapter<RandomizeRecyclerAdapter.RandomizeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RandomizeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_storage, parent, false)
        return RandomizeViewHolder(view)
    }

    override fun getItemCount() = hashtag.size

    override fun onBindViewHolder(holder: RandomizeViewHolder, position: Int) {
        val currentHashtag = hashtag[position]
        holder.itemView.hashContainer.text = currentHashtag.hashtag
        holder.itemView.setOnClickListener {
            viewModel.delete(currentHashtag)
        }

    }

    inner class RandomizeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}