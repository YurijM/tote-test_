package com.example.tote_test.database

import androidx.lifecycle.LiveData
import com.example.tote_test.models.GamblerModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class GamblersLiveData: LiveData<List<GamblerModel>>() {
    private val dbRefGamblers: DatabaseReference = REF_DB_ROOT.child(NODE_GAMBLERS)

    private val listenerGamblers = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                it.getValue(GamblerModel::class.java) ?: GamblerModel()
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }

    override fun onActive() {
        dbRefGamblers.addValueEventListener(listenerGamblers)
        super.onActive()
    }

    override fun onInactive() {
        dbRefGamblers.removeEventListener(listenerGamblers)
        super.onInactive()
    }
}