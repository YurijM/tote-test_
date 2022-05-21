package com.example.tote_test.ui.main.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.tote_test.R
import com.example.tote_test.databinding.FragmentLoginBinding
import com.example.tote_test.utils.APP_ACTIVITY

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gamblersViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.btnLogin.setOnClickListener {
            //APP_ACTIVITY.navController.navigate(R.id.action_navLogin_to_navGamblers)
        }

        binding.txtToRegistration.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_navLogin_to_navSignup)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}