package com.fair.hastag_randomizer.repository.storage.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.view.HashTagViewModel
import kotlinx.android.synthetic.main.recycler_storage.view.*
import java.util.*
import kotlin.collections.ArrayList

class RandomizeRecyclerAdapter(var list: ArrayList<RandomizeEntity>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var filterList = list

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_storage, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = filterList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.itemHolder.text = filterList[position].hashtag
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterList = if (charSearch.isEmpty()) {
                    list
                } else {
                    val resultList = ArrayList<RandomizeEntity>()
                    for (row in list) {
                        if (row.hashtag.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<RandomizeEntity>
                notifyDataSetChanged()
            }

        }
    }

    /**
//    var filterList = ArrayList<String>()
//
//    init {
//        filterList = hashtag
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_storage, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount() = hashtag.size
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val currentHashtag = hashtag[position]
//        holder.itemView.hashContainer.text = currentHashtag
//
////        implement a better delete option, Dialog to confirm?
////
////        holder.itemView.setOnClickListener {
////            viewModel.delete(currentHashtag)
////        }
//
//    }
//
//    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
//
//    // Filtering execution
//
//
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//                filterList = if (charSearch.isEmpty()) {
//                    hashtag
//                } else {
//                    val resultList = ArrayList<String>()
//                    for (row in hashtag) {
//                        if (row.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
//                            resultList.add(row)
//                        }
//                    }
//                    resultList
//                }
//                val filterResults = FilterResults()
//                filterResults.values = filterList
//                return filterResults
//            }
//
//            @Suppress("UNCHECKED_CAST")
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                filterList = results?.values as ArrayList<String>
//                notifyDataSetChanged()
//            }
//
//        }
//    }


*/
}