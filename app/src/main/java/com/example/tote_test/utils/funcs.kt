package com.example.tote_test.utils

import android.widget.Toast
import com.example.tote_test.models.GamblerModel

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_LONG).show()
}

fun checkProfile(gambler: GamblerModel): Boolean =
    !(gambler.nickname.isEmpty()
            || gambler.name.isEmpty()
            || gambler.family.isEmpty()
            || gambler.gender.isEmpty()
            || (gambler.photoUrl.isEmpty() || gambler.photoUrl == "empty")
            )

