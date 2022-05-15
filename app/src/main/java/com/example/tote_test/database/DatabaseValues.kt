package com.example.tote_test.database

import com.example.tote_test.models.GamblerModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference

lateinit var AUTH: FirebaseAuth
lateinit var REF_DB_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var GAMBLER: GamblerModel
lateinit var UID: String

const val FOLDER_GAMBLER_PHOTO = "gamblerPhotos"

const val NODE_GAMBLERS = "gamblers"

const val CHILD_ID = "id"

//Gambler model fields
const val CHILD_NICKNAME = "nickname"
const val CHILD_FAMILY = "family"
const val CHILD_NAME = "name"
const val CHILD_GENDER = "gender"
const val CHILD_PHOTO_URL = "photoUrl"