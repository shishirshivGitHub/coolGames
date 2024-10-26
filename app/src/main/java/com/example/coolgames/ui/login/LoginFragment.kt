package com.example.coolgames.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.coolgames.R
import com.example.coolgames.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val navController = findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment, R.id.signupFragment,
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.signupFragment, R.id.loginFragment))
        binding.btnSignup.setOnClickListener(View.OnClickListener {
            navController.navigate(R.id.signupFragment)
        })
        binding.btnLogin.setOnClickListener(View.OnClickListener {

            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty())
                loginUser(email = email, password = password)
            else {
                Toast.makeText(
                    context,
                    "Enter credentials ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        val navController = findNavController()
        navController.addOnDestinationChangedListener { _, _, _ ->
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Login"
    }

    private fun loginUser(email: String, password: String) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Login Successfull: ",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
                } else {
                    Toast.makeText(
                        context,
                        "Login failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}