package com.example.tote_test.utils

import android.widget.Toast

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_LONG).show()
}

fun checkProfile(): Boolean =
    !(GAMBLER.toString().isNullOrEmpty()
            || GAMBLER.nickname.isEmpty()
            || GAMBLER.name.isEmpty()
            || GAMBLER.family.isEmpty()
            || GAMBLER.gender.isEmpty()
            || GAMBLER.photoUrl.isEmpty()
            || GAMBLER.photoUrl == "empty"
            )

