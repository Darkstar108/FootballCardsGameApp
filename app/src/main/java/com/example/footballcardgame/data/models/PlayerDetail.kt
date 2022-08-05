package com.example.footballcardgame.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "player_detail")
@Parcelize
data class PlayerDetail(
    @PrimaryKey val name: String,
    val nationality: String,
    val position: String,
    val age: Int,
    val imageUrl: String,
    val attack: Int,
    val midfield: Int,
    val defence: Int
): Parcelable