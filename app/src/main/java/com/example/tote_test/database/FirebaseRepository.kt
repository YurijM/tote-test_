package com.example.tote_test.database

import com.example.tote_test.utils.showToast
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

                val dataMap = mutableMapOf<String, Any>()
                dataMap[CHILD_ID] = UID
                dataMap[CHILD_NICKNAME] = ""
                dataMap[CHILD_FAMILY] = ""
                dataMap[CHILD_NAME] = ""
                dataMap[CHILD_GENDER] = ""
                dataMap[CHILD_PHOTO_URL] = ""

                REF_DB_ROOT.child(NODE_GAMBLERS).child(UID).updateChildren(dataMap)
                    .addOnCompleteListener { onSuccess() }
                    .addOnFailureListener { showToast(it.message.toString()) }
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
        REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
    }
}