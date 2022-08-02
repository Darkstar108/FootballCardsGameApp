package com.example.footballcardgame.data.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.footballcardgame.data.daos.PlayerDetailDao
import com.example.footballcardgame.data.models.PlayerDetail

class PlayerDetailRepository(private val playerDetailDao: PlayerDetailDao) {

    // Room executes all queries on a separate thread.
    val playerDetails: LiveData<List<PlayerDetail>> = playerDetailDao.getAllPlayerDetails()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(playerDetail: PlayerDetail) {
        playerDetailDao.insert(playerDetail)
    }

    suspend fun delete(playerDetail: PlayerDetail){
        playerDetailDao.delete(playerDetail)
    }

    suspend fun update(playerDetail: PlayerDetail){
        playerDetailDao.update(playerDetail)
    }
}
