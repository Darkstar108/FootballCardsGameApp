package com.example.footballcardgame.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.footballcardgame.data.daos.PlayerDetailDao
import com.example.footballcardgame.data.models.PlayerDetail
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(PlayerDetail::class), version = 1, exportSchema = false)
public abstract class PlayerDetailRoomDatabase : RoomDatabase() {

    abstract fun playerDetailDao(): PlayerDetailDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PlayerDetailRoomDatabase? = null

        fun getDatabase(context: Context): PlayerDetailRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayerDetailRoomDatabase::class.java,
                    "player_detail_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
