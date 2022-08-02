package com.example.footballcardgame.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.footballcardgame.MainActivity
import com.example.footballcardgame.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splashScreenActivity = this

        object: CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                val homeIntent = Intent(baseContext, MainActivity::class.java)

                splashScreenActivity.startActivity(homeIntent)
                splashScreenActivity.finish()
            }
        }.start()
    }
}