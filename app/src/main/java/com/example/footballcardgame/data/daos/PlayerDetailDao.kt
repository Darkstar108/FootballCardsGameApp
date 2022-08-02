package com.example.footballcardgame.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.footballcardgame.data.models.PlayerDetail

@Dao
interface PlayerDetailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(playerDetail: PlayerDetail)

    @Delete
    suspend fun delete(playerDetail: PlayerDetail)

    @Query("Select * from player_detail order by name ASC")
    fun getAllPlayerDetails(): LiveData<List<PlayerDetail>>

    @Query("Delete from player_detail")
    suspend fun deleteAll()

    @Update
    suspend fun update(playerDetail: PlayerDetail)
}