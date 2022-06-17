package com.example.tote_test.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference

lateinit var REPOSITORY: FirebaseRepository
lateinit var AUTH: FirebaseAuth
lateinit var REF_DB_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var UID: String

const val MIN_LENGTH_PASSWORD = 6

lateinit var EMAIL: String
lateinit var PASSWORD: String
lateinit var NICKNAME: String

const val FOLDER_GAMBLER_PHOTO = "gamblerPhotos"

const val NODE_GAMBLERS = "gamblers"

const val CHILD_ID = "id"

//Gambler model fields
const val GAMBLER_NICKNAME = "nickname"
const val GAMBLER_FAMILY = "family"
const val GAMBLER_NAME = "name"
const val GAMBLER_GENDER = "gender"
const val GAMBLER_PHOTO_URL = "photoUrl"