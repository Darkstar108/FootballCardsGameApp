package com.example.footballcardgame.data.models

data class PlayerDetail(
    val name: String,
    val nationality: String,
    val position: String,
    val age: Int,
    val imageUrl: String,
    val attack: Int,
    val midfield: Int,
    val defence: Int
)