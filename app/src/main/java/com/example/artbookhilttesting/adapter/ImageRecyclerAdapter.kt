package com.example.artbookhilttesting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbookhilttesting.databinding.ImageRowItemLayoutBinding
import com.example.artbookhilttesting.model.ArtModel
import javax.inject.Inject

class ImageRecyclerAdapter
@Inject
constructor( val glide : RequestManager) : RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder>(){

    /* create onClick of item */
    private var onItemClickListener : ((String) -> Unit ) ? = null

    class ImageViewHolder( var binding : ImageRowItemLayoutBinding ) : RecyclerView.ViewHolder(binding.root)

    /* diffUtil operation */
    private val diffUtil = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)
    var images : List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ImageViewHolder {
       return ImageViewHolder(ImageRowItemLayoutBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup , false))
    }

    /* handle function of click listener */
    fun setOnItemClickListener( listener : ( String ) -> Unit ){
        onItemClickListener = listener
    }
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = images[position]
        holder.itemView.apply {
            glide.load(imageUrl).into(holder.binding.ivSingleArtAmage)
            setOnClickListener {
                onItemClickListener?.let {
                    it(imageUrl)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}