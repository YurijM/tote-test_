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
        //FragmentManager.POP_BACK_STACK_INCLUSIVE
        //APP_ACTIVITY.navController.clearBackStack(R.id.navLogin)
        //APP_ACTIVITY.supportFragmentManager.popBackStack()

        /*var count: Int = APP_ACTIVITY.supportFragmentManager.backStackEntryCount
        Log.i("Stack count", count.toString())
        while (count > 0) {
            APP_ACTIVITY.supportFragmentManager.popBackStack()
            count--
            Log.i("Stack count", count.toString())
        }*/

        val gamblersViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.txtToRegistration.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.navSignup)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}