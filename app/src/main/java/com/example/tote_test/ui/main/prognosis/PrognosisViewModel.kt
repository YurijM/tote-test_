package com.example.tote_test.ui.main.prognosis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrognosisViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Прогноз"
    }
    val text: LiveData<String> = _text
}