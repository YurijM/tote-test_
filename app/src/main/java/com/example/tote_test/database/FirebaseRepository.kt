package com.example.tote_test.database

import android.util.Log
import com.example.tote_fifa_2022.utilits.AppPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

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
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
                initFirebase()
                onSuccess()
            }
            .addOnFailureListener {
                onFail(it.message.toString())
            }
    }

    fun signOut() {
        AUTH.signOut()
    }

    fun initFirebase() {
        UID = AUTH.currentUser?.uid.toString()
        REF_DB_ROOT = FirebaseDatabase.getInstance().reference
        REF_USER_ROOT = REF_DB_ROOT.child(NODE_GAMBLERS).child(UID)

        REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
    }
}