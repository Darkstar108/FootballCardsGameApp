package com.example.footballcardgame.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.footballcardgame.data.databases.PlayerDetailRoomDatabase
import com.example.footballcardgame.data.models.PlayerDetail
import com.example.footballcardgame.data.repositories.PlayerDetailRepository
import kotlinx.coroutines.launch

class PlayerListViewModel(application: Application) : AndroidViewModel(application) {

    val playerDetails: LiveData<List<PlayerDetail>>
    val playerDetailRepository: PlayerDetailRepository

    init {
        val playerDetailDao = PlayerDetailRoomDatabase.getDatabase(application).playerDetailDao()
        playerDetailRepository = PlayerDetailRepository(playerDetailDao)
        playerDetails = playerDetailRepository.playerDetails
    }

    fun insert(playerDetail: PlayerDetail) = viewModelScope.launch {
        playerDetailRepository.insert(playerDetail)
    }

    fun delete(playerDetail: PlayerDetail) = viewModelScope.launch {
        playerDetailRepository.delete(playerDetail)
    }

    fun update(playerDetail: PlayerDetail) = viewModelScope.launch {
        playerDetailRepository.update(playerDetail)
    }

}