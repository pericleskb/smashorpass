package com.monkeydonkey.smashorpass.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.monkeydonkey.smashorpass.databinding.PokemonChoiceCardBinding
import com.monkeydonkey.smashorpass.models.Pokemon

class PokemonCardFragment: Fragment() {

    private val pokemon: Pokemon
        get() = requireArguments().getParcelable(POKEMON_TAG)
            ?: throw IllegalArgumentException("Argument $POKEMON_TAG required")

    companion object {
        private const val POKEMON_TAG = "shown_pokemon"
        fun newInstance(pokemon: Pokemon) = PokemonCardFragment().apply {
            arguments = bundleOf(
                POKEMON_TAG to pokemon
            )
        }
    }

    private var _binding: PokemonChoiceCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PokemonChoiceCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.pokemonImage.setImageBitmap(this.pokemon.frontSecondary)
    }
}