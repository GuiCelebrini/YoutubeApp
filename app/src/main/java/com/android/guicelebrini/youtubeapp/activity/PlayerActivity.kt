package com.android.guicelebrini.youtubeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.guicelebrini.youtubeapp.R
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val bundle = intent.extras

        player_title.text = bundle?.getString("title")
    }
}