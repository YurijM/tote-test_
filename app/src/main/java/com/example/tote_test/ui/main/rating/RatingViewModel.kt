package com.example.tote_test.ui.main.rating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RatingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Рейтинг"
    }
    val text: LiveData<String> = _text
}