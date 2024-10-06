package com.turing.alan.cpifp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.Champion

class ChampionAdapter(private val champions: List<Champion>) : RecyclerView.Adapter<ChampionAdapter.ChampionViewHolder>() {

    inner class ChampionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewChampion: ImageView = itemView.findViewById(R.id.image_view_champion)
        val nameTextView: TextView = itemView.findViewById(R.id.text_view_name)
        val titleTextView: TextView = itemView.findViewById(R.id.text_view_title)
        val loreTextView: TextView = itemView.findViewById(R.id.text_view_lore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_champion, parent, false)
        return ChampionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        val champion = champions[position]
        holder.nameTextView.text = champion.name
        holder.titleTextView.text = champion.title
        holder.loreTextView.text = champion.lore

        // Cargar la imagen del campeón usando Coil
        holder.imageViewChampion.load(champion.imageUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }

        holder.imageViewChampion.contentDescription = "Imagen de ${champion.name}"
    }

    override fun getItemCount(): Int = champions.size
}
