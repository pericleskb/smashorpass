package com.monkeydonkey.smashorpass

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.monkeydonkey.smashorpass.databinding.MainActivityBinding
import com.monkeydonkey.smashorpass.ui.MainViewModel
import com.monkeydonkey.smashorpass.ui.fragments.ChoiceFragment
import com.monkeydonkey.smashorpass.ui.fragments.ListFragment

class MainActivity: FragmentActivity() {

    private lateinit var binding: MainActivityBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        createNavigationListener()
        if (savedInstanceState == null) addStartingFragment()
    }

    private fun createNavigationListener() {
        //TODO change to Navigation library
        binding.navigation.setOnItemSelectedListener { item ->
            val fragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container)
            when(item.itemId) {
                R.id.menu_view_choice -> {
                    if (fragment !is ChoiceFragment)
                        changeToFragment(ChoiceFragment.newInstance())
                    true
                }
                R.id.menu_view_list -> {
                    if (fragment !is ListFragment)
                        changeToFragment(ListFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }

    private fun addStartingFragment() {
        supportFragmentManager.beginTransaction().add(
            binding.mainFragmentContainer.id, ChoiceFragment.newInstance()).commit()
    }

    private fun changeToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            binding.mainFragmentContainer.id, fragment).commit()
    }
}