package com.example.tote_test.models

class GamblerModel (
    var id: String = "",
    var nickname: String = "",
    var family: String = "",
    var name: String = "",
    var gender: String = "",
    var photoUrl: String = "empty"  //Для Picasso нужно, чтобы имелось какое-то значение пока игрок ещё не добавлен
)