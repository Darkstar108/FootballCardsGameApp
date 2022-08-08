package com.example.footballcardgame.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.footballcardgame.R
import com.example.footballcardgame.common.Constants
import com.example.footballcardgame.data.models.PlayerDetail
import com.example.footballcardgame.ui.viewModels.PlayerListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var playerListViewModel: PlayerListViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        playerListViewModel = ViewModelProvider(this)[PlayerListViewModel::class.java]
        sharedPreferences = this.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        val hasData = sharedPreferences.getBoolean("hasData", false)


        CoroutineScope(Dispatchers.IO).launch {
            if (!hasData)
            {
                val playerDetails = arrayListOf<PlayerDetail>(
                    PlayerDetail("Lionel Messi","Argentina","Forward",33,"https://pesdb.net/pes2021/images/players/7511.png",99,85,20),
                    PlayerDetail("Kevin De Bruyne","Belgium","Midfielder",29,"https://pesdb.net/pes2021/images/players/44379.png",80,95,60),
                    PlayerDetail("Neymar","Brazil","Forward",28,"https://pesdb.net/pes2021/images/players/40352.png",90,75,30),
                    PlayerDetail("Kylian Mbappe","France","Forward",22,"https://pesdb.net/pes2021/images/players/110718.png",95,65,25),
                    PlayerDetail("Cristiano Ronaldo","Portugal","Forward",35,"https://pesdb.net/pes2021/images/players/4522.png",95,55,20),
                )

                for(playerDetail in playerDetails!!) {
                    playerListViewModel.insert(playerDetail)
                }

                editor = sharedPreferences.edit()
                editor.putBoolean("hasData",true)
                editor.commit()
            }
            delay(1000)
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}