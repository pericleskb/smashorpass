package com.monkeydonkey.smashorpass.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.monkeydonkey.smashorpass.models.Pokemon
import com.monkeydonkey.smashorpass.ui.fragments.PokemonCardFragment

class PokemonCardsAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val pokemonList = mutableListOf<Pokemon>()

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun createFragment(position: Int): Fragment {
        return if (pokemonList.isEmpty()) {
            //TODO change with empty fragment
            Fragment()
        } else {
            PokemonCardFragment.newInstance(pokemonList[position])
        }
    }

    fun addToList(newItems: List<Pokemon>) {
        pokemonList.addAll(newItems)
    }
}