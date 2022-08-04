package com.example.footballcardgame.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.footballcardgame.data.databases.PlayerDetailRoomDatabase
import com.example.footballcardgame.data.models.PlayerDetail
import com.example.footballcardgame.data.repositories.PlayerDetailRepository
import kotlinx.coroutines.launch

class AddPlayerViewModel(application: Application) : AndroidViewModel(application) {

}