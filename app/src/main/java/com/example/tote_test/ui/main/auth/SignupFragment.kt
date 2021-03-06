package com.example.tote_test.ui.main.auth

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tote_fifa_2022.utilits.AppPreferences
import com.example.tote_test.R
import com.example.tote_test.database.EMAIL
import com.example.tote_test.database.MIN_LENGTH_PASSWORD
import com.example.tote_test.database.PASSWORD
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
                if (checkFieldEmpty(it, binding.signupLayoutPassword, getString(R.string.password))) {
                    checkMinLength(it, binding.signupLayoutPassword, getString(R.string.password))
                }
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
                if (comparePassword(password, confirmPassword)) {
                    EMAIL = email
                    PASSWORD = password

                    signup()
                }
            } else {
                showToast(getString(R.string.error_all_required))
            }
        }
    }

    private fun checkMinLength(input: Editable,
                               layout: TextInputLayout,
                               fieldName: String): Boolean {
        var result = true

        if (input.length < 6) {
            layout.isErrorEnabled = true
            layout.error = getString(R.string.error_min_length, fieldName, MIN_LENGTH_PASSWORD)
            result = false
        } else {
            layout.isErrorEnabled = false
        }

        return result
    }

    private fun signup() {
        vmSignup.signup {
            AppPreferences.setAuth(true)

            APP_ACTIVITY.navController.navigate(R.id.action_navSignup_to_navProfile)
        }
    }

    private fun checkFieldEmpty(input: Editable,
                                layout: TextInputLayout,
                                fieldName: String): Boolean {
        var result = true

        if (input.isEmpty()) {
            layout.isErrorEnabled = true
            layout.error = getString(R.string.error_field_empty, fieldName)
            result = false
        } else{
            layout.isErrorEnabled = false
        }

        return result
    }

    private fun comparePassword(password: String, confirmPassword: String): Boolean {
        var result = true

        if (password != confirmPassword) {
            binding.signupLayoutConfirmPassword.isErrorEnabled = true
            binding.signupLayoutConfirmPassword.error = getString(R.string.error_confirm_password)
            result = false
        } else {
            binding.signupLayoutConfirmPassword.isErrorEnabled = false
        }

        return result
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}