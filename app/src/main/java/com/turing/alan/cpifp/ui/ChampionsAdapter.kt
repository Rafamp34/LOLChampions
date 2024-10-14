package com.turing.alan.cpifp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.turing.alan.cpifp.data.Champion
import com.turing.alan.cpifp.databinding.ChampionListItemBinding

class ChampionsAdapter(private val onChampionClick: (Champion) -> Unit)
    : ListAdapter<Champion, ChampionsAdapter.ChampionItemViewHolder>(ChampionDiffCallback) {

    class ChampionItemViewHolder(private val binding: ChampionListItemBinding)
        : ViewHolder(binding.root) {

        fun bind(champion: Champion, onChampionClick: (Champion) -> Unit) {
            binding.championTitle.text = champion.title
            binding.championName.text = champion.name
            binding.championImage.load(champion.imageUrl)

            // Configurar el listener para el clic en la tarjeta
            binding.root.setOnClickListener {
                onChampionClick(champion) // Llamar a la función cuando se hace clic
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionItemViewHolder {
        val binding = ChampionListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChampionItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionItemViewHolder, position: Int) {
        holder.bind(getItem(position), onChampionClick)
    }

    object ChampionDiffCallback : DiffUtil.ItemCallback<Champion>() {
        override fun areItemsTheSame(oldItem: Champion, newItem: Champion) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Champion, newItem: Champion) =
            oldItem.name == newItem.name &&
                    oldItem.imageUrl == newItem.imageUrl &&
                    oldItem.title == newItem.title
    }
}
