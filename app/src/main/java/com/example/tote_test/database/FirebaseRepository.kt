package com.example.tote_test.database

import com.example.tote_fifa_2022.utilits.AppPreferences
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepository {
    init {
        AUTH = FirebaseAuth.getInstance()
    }

    fun signup(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
                UID = AUTH.currentUser?.uid.toString()
                onSuccess()
            }
            .addOnFailureListener {
                onFail(it.message.toString())
            }
    }

    fun login(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        if (AppPreferences.getAuth()) {
            UID = AUTH.currentUser?.uid.toString()
            onSuccess()
        } else {
            AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener {
                    UID = AUTH.currentUser?.uid.toString()
                    onSuccess()
                }
                .addOnFailureListener {
                    onFail(it.message.toString())
                }
        }
    }

    fun signOut(){
        AUTH.signOut()
    }
}