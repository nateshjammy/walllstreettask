package com.wallstreet.main.home.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wallstreet.R
import com.wallstreet.data.model.University
import kotlinx.android.synthetic.main.item_layout.view.*

class UniversityAdapter: PagedListAdapter<University, UniversityAdapter.DataViewHolder>(DIFF_CALLBACK) {


    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<University>() {

            override fun areItemsTheSame(
                oldItem: University,
                newItem: University
              ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: University,
                newItem: University
            ) = oldItem == newItem
        }
    }



    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(university: University) {
            itemView.apply {
                textViewName.text = university.name
                textViewCountry.text  = university.country
                textViewwebpage.text  = university.web_pages[0]
            }


            itemView.setOnClickListener(View.OnClickListener {
                val bundle: Bundle = bundleOf(
                    "name" to university.name,
                    "domain" to university.domains[0],
                    "country" to university.country)
                if(!bundle.isEmpty()){
                    it.findNavController().
                    navigate(R.id.action_homeFragment_to_detatilFragment,bundle)
                }

            })

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


}

