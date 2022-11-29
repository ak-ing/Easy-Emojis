package com.aking.easilyemojis.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aking.easilyemojis.bean.Pic
import com.aking.easilyemojis.databinding.ItemPagingPicBinding

/**
 * Created by Rick on 2022-11-29  14:42.
 * God bless my code!
 * Description: 图片分页适配器
 */
class PicPagingAdapter : PagingDataAdapter<Pic, PicPagingAdapter.InnerHolder>(COMPARATOR) {
    private object COMPARATOR : DiffUtil.ItemCallback<Pic>() {
        override fun areItemsTheSame(oldItem: Pic, newItem: Pic) = oldItem.create_time == newItem.create_time

        override fun areContentsTheSame(oldItem: Pic, newItem: Pic) = oldItem == newItem
    }

    inner class InnerHolder(val binding: ItemPagingPicBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.binding.item = getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        InnerHolder(ItemPagingPicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}