package com.android.guicelebrini.youtubeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.guicelebrini.youtubeapp.R
import com.android.guicelebrini.youtubeapp.adapter.AdapterRecyclerVideos
import com.android.guicelebrini.youtubeapp.model.Video
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var videosList : ArrayList<Video> = ArrayList()
    private lateinit var adapter : AdapterRecyclerVideos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureToolbar()

        createVideosList()

        configureRecyclerVideos()

    }

    private fun configureToolbar(){
        var toolbarMain : Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbarMain)
    }

    private fun createVideosList(){
        videosList.add(Video("Entrevista com Zangado", "https://i.ytimg.com/vi/RGZKj3E1CeI/maxresdefault.jpg"))
        videosList.add(Video("Primeiro vídeo do canal", "https://i.ytimg.com/vi/SNbZdDqgCn8/maxresdefault.jpg"))
    }

    private fun configureRecyclerVideos(){
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        adapter = AdapterRecyclerVideos(videosList)

        recycler_videos.layoutManager = layoutManager
        recycler_videos.setHasFixedSize(true)
        recycler_videos.adapter = adapter
    }
}