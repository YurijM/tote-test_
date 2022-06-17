package com.example.tote_test.database

import androidx.lifecycle.MutableLiveData
import com.example.tote_test.models.GamblerModel
import com.example.tote_test.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
                dataMap[GAMBLER_NICKNAME] = ""
                dataMap[GAMBLER_FAMILY] = ""
                dataMap[GAMBLER_NAME] = ""
                dataMap[GAMBLER_GENDER] = ""
                dataMap[GAMBLER_PHOTO_URL] = ""

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

    /*
    Gamblers Repository
     */

    fun getGambler(liveData: MutableLiveData<GamblerModel>) {
        REF_DB_ROOT.child(NODE_GAMBLERS).child(UID).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val gambler: GamblerModel = snapshot.getValue(GamblerModel::class.java) ?: GamblerModel()

                liveData.postValue(gambler)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun updateGambler(gambler: GamblerModel, onSuccess: () -> Unit) {
        val node = REF_DB_ROOT.child(NODE_GAMBLERS).child(UID)

        val mapGambler = hashMapOf<String, Any>()
        mapGambler[GAMBLER_NICKNAME] = gambler.nickname
        mapGambler[GAMBLER_FAMILY] = gambler.family
        mapGambler[GAMBLER_NAME] = gambler.name
        mapGambler[GAMBLER_GENDER] = gambler.gender
        mapGambler[GAMBLER_PHOTO_URL] = gambler.photoUrl

        node.updateChildren(mapGambler)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }
}