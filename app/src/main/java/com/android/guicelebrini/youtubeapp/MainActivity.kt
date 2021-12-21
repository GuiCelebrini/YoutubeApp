package com.android.guicelebrini.youtubeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureToolbar()

    }

    private fun configureToolbar(){
        var toolbarMain : Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbarMain)
    }
}