package com.example.tote_test.models

data class GamblerModel (
    val id: String = "",
    val nickname: String = "",
    val family: String = "",
    val name: String = "",
    val gender: String = "",
    val photoUrl: String = "empty"  //Для Picasso нужно, чтобы имелось какое-то значение пока игрок ещё не добавлен
)