package com.example.tote_test.ui.main.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tote_test.database.REPOSITORY
import com.example.tote_test.utils.showToast

class SignupViewModel : ViewModel() {
    fun signup(onSuccess: () -> Unit) {
        REPOSITORY.signup(
            { onSuccess() },
            { showToast(it) }
        )
    }
}