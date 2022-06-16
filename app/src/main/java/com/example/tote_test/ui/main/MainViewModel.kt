package com.example.tote_test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tote_test.database.REPOSITORY
import com.example.tote_test.models.GamblerModel

class MainViewModel: ViewModel() {
    private val _currentGambler = MutableLiveData<GamblerModel>()
    val currentGambler: LiveData<GamblerModel> = _currentGambler

    fun getGambler() {
        REPOSITORY.getGambler(_currentGambler)
    }

    fun signOut() {
        REPOSITORY.signOut()
    }
}