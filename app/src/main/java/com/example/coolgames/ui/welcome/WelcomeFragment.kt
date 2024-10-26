package com.example.coolgames.ui.welcome


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coolgames.R
import com.example.coolgames.databinding.FragmentSuperBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class WelcomeFragment : Fragment() {

    private var _binding: FragmentSuperBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView: BottomNavigationView = binding.navView
        childFragmentManager.beginTransaction()
            .replace(binding.nestedFragmentContainer.id, NestedFragment1())
            .commit()
        if (savedInstanceState == null) {
            bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.navigation_home -> {
                        childFragmentManager.beginTransaction()
                            .replace(binding.nestedFragmentContainer.id, NestedFragment1())
                            .commit()
                        true
                    }

                    R.id.navigation_dashboard -> {
                        childFragmentManager.beginTransaction()
                            .replace(binding.nestedFragmentContainer.id, NestedFragment2())
                            .commit()
                        true
                    }

                    R.id.navigation_notifications -> {
                        childFragmentManager.beginTransaction()
                            .replace(binding.nestedFragmentContainer.id, NestedFragment3())
                            .commit()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
