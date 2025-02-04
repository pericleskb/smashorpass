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
import androidx.recyclerview.widget.LinearLayoutManager
import com.monkeydonkey.smashorpass.databinding.ChoiceFragmentBinding
import com.monkeydonkey.smashorpass.ui.MainViewModel
import com.monkeydonkey.smashorpass.ui.adapters.ChoiceAdapter
import kotlinx.coroutines.launch

class ChoiceFragment: Fragment() {

    companion object {
        fun newInstance() = ChoiceFragment()
    }

    private var _binding: ChoiceFragmentBinding? = null
    private val binding get() = _binding!!
    private val choiceAdapter = ChoiceAdapter()

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChoiceFragmentBinding.inflate(inflater, container, false)


        binding.pokemonProfileRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.pokemonProfileRecyclerView.adapter = choiceAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainState.collect {
                    choiceAdapter.addToList(it.pokeList)
                    choiceAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}