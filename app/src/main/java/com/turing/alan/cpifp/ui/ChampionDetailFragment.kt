package com.turing.alan.cpifp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.turing.alan.cpifp.R
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.turing.alan.cpifp.data.InMemoryChampionsRepository

class ChampionDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_champion_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val championId = arguments?.getInt("champion_id")

        val repository = InMemoryChampionsRepository.getInstance()

        val champion = repository.getChampions().find { it.id == championId }
        if (champion != null) {
            view.findViewById<TextView>(R.id.champion_name).text = champion.name
            view.findViewById<TextView>(R.id.champion_title).text = champion.title
            view.findViewById<TextView>(R.id.champion_lore).text = champion.lore
            view.findViewById<ImageView>(R.id.champion_image).load(champion.imageUrl)
        }
    }
}
