package com.monkeydonkey.smashorpass.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.monkeydonkey.smashorpass.databinding.ChoiceFragmentBinding
import com.monkeydonkey.smashorpass.ui.MainViewModel
import kotlinx.coroutines.launch

class ChoiceFragment : Fragment() {

    companion object {
        fun newInstance() = ChoiceFragment()
    }

    private var _binding: ChoiceFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

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
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainState.collect {
                    if (it.pokeList.isNotEmpty()) {
                        childFragmentManager.beginTransaction().add(
                            binding.fragmentContainerView.id, PokemonCardFragment
                                .newInstance(it.pokeList[0])
                        ).commit()
                    } else {
                        //TODO show loading fragment
                    }
                }
            }
        }

        binding.fragmentContainerView.apply {
            setOnLongClickListener { pokemonCardFragment ->
                val shadow = View.DragShadowBuilder(this)
                pokemonCardFragment.startDragAndDrop(null, shadow, null, 0)
            }

            setOnDragListener { v, dragEvent ->
                Log.d("@@@", dragEvent.action.toString())
                true
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}