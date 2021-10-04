package com.example.artbookhilttesting.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbookhilttesting.databinding.ArtRowItemLayoutBinding
import com.example.artbookhilttesting.model.ArtModel
import javax.inject.Inject

class ArtRecyclerAdapter
@Inject
constructor( val glide : RequestManager) : RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>(){


    class ArtViewHolder( var binding : ArtRowItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    /* diffUtil operation */
    private val diffUtil = object : DiffUtil.ItemCallback<ArtModel>(){
        override fun areItemsTheSame(oldItem: ArtModel, newItem: ArtModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArtModel, newItem: ArtModel): Boolean {
            return oldItem == newItem
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

    var arts : List<ArtModel>
    get() = recyclerListDiffer.currentList
    set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ArtViewHolder {
        return ArtViewHolder(ArtRowItemLayoutBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val artList = arts[position]

        holder.binding.apply {
            glide.load(artList.imageUrl).into(artRowImageView)
            artRowArtNameText.text      = "Name: ${artList.name}"
            artRowArtistNameText.text   = "Artist Name: ${artList.artisName}"
            artRowYearText.text         = "Year: ${artList.year}"
        }
    }

    override fun getItemCount(): Int {
       return arts.size
    }
}