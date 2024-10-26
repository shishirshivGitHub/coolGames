package com.example.coolgames.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coolgames.databinding.FragmentNestedBinding
import com.google.firebase.auth.FirebaseAuth

class NestedFragment1 : Fragment() {

    private var _binding: FragmentNestedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNestedBinding.inflate(inflater, container, false)
        val user = FirebaseAuth.getInstance().currentUser
        val userEmail = user?.email
        binding.user.text = "User : " + userEmail
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
