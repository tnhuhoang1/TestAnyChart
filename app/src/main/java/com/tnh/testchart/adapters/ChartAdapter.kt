package com.tnh.testchart.adapters

import com.bumptech.glide.Glide
import com.tnh.testchart.R
import com.tnh.testchart.databinding.ChartItemBinding
import com.tnh.testchart.models.ChartData
import com.tnh.tnhlibrary.dataBinding.recycler.DataBindingViewHolder
import com.tnh.tnhlibrary.dataBinding.recycler.IdDataBindingListAdapter

class ChartAdapter: IdDataBindingListAdapter<ChartData, ChartItemBinding>(R.layout.chart_item) {
    var onItemClicked: (chartItem: ChartData) -> Unit = {}

    override fun onBindViewHolder(holder: DataBindingViewHolder<ChartItemBinding>, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            chartItemTitle.text = item.title
            Glide.with(chartItemThumbnail.context).asBitmap().load(item.thumbnail).into(chartItemThumbnail)

            root.setOnClickListener {
                onItemClicked(item)
            }
        }
    }
}