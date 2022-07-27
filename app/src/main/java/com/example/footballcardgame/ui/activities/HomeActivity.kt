package com.example.footballcardgame.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.footballcardgame.R

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val playGameButton: Button = findViewById(R.id.home_game_button)
        val playerListButton: Button = findViewById(R.id.home_player_list_button)
        playGameButton.setOnClickListener(this)
        playerListButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.home_game_button -> {

            }
            R.id.home_player_list_button -> {
                val playerListintent = Intent(baseContext, PlayerListActivity::class.java)
                startActivity(playerListintent)
            }
        }
    }
}