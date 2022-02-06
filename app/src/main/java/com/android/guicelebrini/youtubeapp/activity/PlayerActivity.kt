package com.android.guicelebrini.youtubeapp.activity

import android.os.Bundle
import android.util.Log
import com.android.guicelebrini.youtubeapp.R
import com.android.guicelebrini.youtubeapp.helper.YoutubeInfos
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener{

    lateinit var videoId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val bundle = intent.extras

        videoId = bundle?.getString("videoId").toString()


        if (bundle != null){
            player.initialize(YoutubeInfos.API_KEY, this)
        }
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, wasPlaying: Boolean){

        youtubePlayer?.setFullscreen(true)
        youtubePlayer?.setShowFullscreenButton(false)
        youtubePlayer?.loadVideo(videoId)

    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?){
        Log.e("Erro", "onInitializationFailure: ${p1.toString()}" )
    }
}