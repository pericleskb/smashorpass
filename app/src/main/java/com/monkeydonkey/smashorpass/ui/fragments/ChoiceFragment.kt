package com.monkeydonkey.smashorpass.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.monkeydonkey.smashorpass.databinding.ChoiceFragmentBinding
import com.monkeydonkey.smashorpass.ui.MainViewModel
import com.monkeydonkey.smashorpass.ui.adapters.PokemonCardsAdapter
import kotlinx.coroutines.launch

class ChoiceFragment : Fragment() {

    companion object {
        fun newInstance() = ChoiceFragment()
    }

    private var _binding: ChoiceFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var pokemonCardsAdapter: PokemonCardsAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChoiceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonCardsAdapter = PokemonCardsAdapter(this)
        viewPager = binding.pokemonViewPager
        viewPager.adapter = pokemonCardsAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainState.collect {
                    if (it.pokeList.isNotEmpty()) {
                        pokemonCardsAdapter.run {
                            addToList(it.pokeList)
                            notifyItemRangeInserted(itemCount, it.pokeList.size)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}