package com.droidsmith.hogwartsarchive.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.droidsmith.hogwartsarchive.R
import com.droidsmith.hogwartsarchive.data.Character
import com.droidsmith.hogwartsarchive.databinding.CharacterItemBinding



class CharacterAdapter (
    private val listener: OnItemClickListener
        ):
    ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

   inner class CharacterViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(character: Character) {
            binding.apply {
                tvCharacterName.text = character.name
                Glide.with(this.root)
                    .load(character.image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.image_not_found)
                    .override(500, 500)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(IvCharacterImage)
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }

}