package com.example.tote_test.database

import com.example.tote_test.models.GamblerModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DB_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
    GAMBLER = GamblerModel()
    UID = AUTH.currentUser?.uid.toString()
}