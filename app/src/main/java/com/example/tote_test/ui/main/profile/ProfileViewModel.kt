package com.example.tote_test.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tote_test.database.REPOSITORY
import com.example.tote_test.models.GamblerModel
import com.example.tote_test.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _inProgress = MutableLiveData(false)
    val inProgress: LiveData<Boolean> = _inProgress

    fun update(gambler: GamblerModel, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.Main) {
            showProgress()
            REPOSITORY.updateGambler(gambler) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
            viewModelScope.launch(Dispatchers.Main) {
                hideProgress()
            }
        }

    private fun hideProgress() {
        _inProgress.value = false
    }

    private fun showProgress() {
        _inProgress.value = true
    }
}