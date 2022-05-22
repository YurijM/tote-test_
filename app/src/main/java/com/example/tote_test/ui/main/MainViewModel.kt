package com.example.tote_test.ui.main

import androidx.lifecycle.ViewModel
import com.example.tote_test.database.REPOSITORY

class MainViewModel: ViewModel() {
    fun signOut() {
        REPOSITORY.signOut()
    }
}