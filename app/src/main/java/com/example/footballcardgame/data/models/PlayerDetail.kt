package com.example.footballcardgame.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_detail")
data class PlayerDetail(
    @PrimaryKey
    val name: String,
    val nationality: String,
    val position: String,
    val age: Int,
    val imageUrl: String,
    val attack: Int,
    val midfield: Int,
    val defence: Int
)