package com.example.tote_test.ui.main.auth

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tote_test.R
import com.example.tote_test.databinding.FragmentSignupBinding
import com.example.tote_test.utils.APP_ACTIVITY
import com.example.tote_test.utils.showToast
import com.google.android.material.textfield.TextInputLayout

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var vmSignup: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        vmSignup = ViewModelProvider(this)[SignupViewModel::class.java]

        binding.signupInputEmail.addTextChangedListener {
            if (it != null) {
                checkFieldEmpty(it, binding.signupLayoutEmail, getString(R.string.email))
            }
        }

        binding.signupInputPassword.addTextChangedListener {
            if (it != null) {
                checkFieldEmpty(it, binding.signupLayoutPassword, getString(R.string.password))
            }
        }

        binding.signupInputConfirmPassword.addTextChangedListener {
            if (it != null) {
                checkFieldEmpty(it, binding.signupLayoutConfirmPassword, getString(R.string.confirm_password))
            }
        }

        binding.btnSignup.setOnClickListener {
            val email = binding.signupInputEmail.text.toString().trim()
            val password = binding.signupInputPassword.text.toString().trim()
            val confirmPassword = binding.signupInputConfirmPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                val error = comparePassword(password, confirmPassword)

                if (!error) {
                    APP_ACTIVITY.navController.navigate(R.id.navGamblers)
                }
            } else {
                showToast(getString(R.string.error_all_required))
            }
        }
    }

    private fun checkFieldEmpty(input: Editable, layout: TextInputLayout, fieldName: String) {
        if (input.isEmpty()) {
            layout.error = getString(R.string.error_field_empty, fieldName)
        } else {
            layout.error = ""
        }
    }

    /*private fun checkFields(email: String, password: String, confirmPassword: String): Boolean {
        var error = false

        val layoutEmail = binding.signupLayoutEmail
        if (email.isEmpty()) {
            layoutEmail.error = getString(R.string.error_field_empty, getString(R.string.email))
            error = true
        } else
            layoutEmail.error = ""

        val layoutPassword = binding.signupLayoutPassword
        if (password.isEmpty()) {
            layoutPassword.error = getString(R.string.error_field_empty, getString(R.string.password))
            error = true
        } else
            layoutPassword.error = ""

        val layoutConfirmPassword = binding.signupLayoutConfirmPassword
        if (confirmPassword.isEmpty()) {
            layoutConfirmPassword.error = getString(R.string.error_field_empty, getString(R.string.confirm_password))
            error = true
        } else
            layoutConfirmPassword.error = ""

        if (error) {
            showToast(getString(R.string.error_all_required))
        } else {
            error = comparePassword(password, confirmPassword)
        }

        return error
    }*/

    private fun comparePassword(password: String, confirmPassword: String): Boolean {
        var error = false

        if (password != confirmPassword) {
            binding.signupLayoutPassword.error = getString(R.string.error_confirm_password)
            binding.signupLayoutConfirmPassword.error = getString(R.string.error_confirm_password)

            error = true
        }

        return error
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}