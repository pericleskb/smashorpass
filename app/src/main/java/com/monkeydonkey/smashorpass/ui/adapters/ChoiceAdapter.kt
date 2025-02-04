package com.monkeydonkey.smashorpass.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.monkeydonkey.smashorpass.R
import com.monkeydonkey.smashorpass.models.Pokemon

class ChoiceAdapter():
    RecyclerView.Adapter<ChoiceAdapter.PokemonProfileViewHolder>() {

    private val pokemonList = mutableListOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonProfileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_choice_card, parent, false)
        return PokemonProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonProfileViewHolder, position: Int) {
        holder.image.setImageBitmap(pokemonList[position].frontMain)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class PokemonProfileViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.pokemon_image)
    }

    fun addToList(newItems: List<Pokemon>) {
        pokemonList.addAll(newItems)
    }
}