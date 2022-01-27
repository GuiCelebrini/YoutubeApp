package com.android.guicelebrini.youtubeapp.activity

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionProvider
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.guicelebrini.youtubeapp.R
import com.android.guicelebrini.youtubeapp.adapter.AdapterRecyclerVideos
import com.android.guicelebrini.youtubeapp.helper.YoutubeInfos
import com.android.guicelebrini.youtubeapp.model.Video
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var videosList : ArrayList<Video> = ArrayList()
    private lateinit var adapter : AdapterRecyclerVideos

    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofit = Retrofit.Builder().baseUrl(YoutubeInfos.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        configureToolbar()

        createVideosList()

        configureRecyclerVideos()

    }

    private fun configureToolbar(){
        var toolbarMain : Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbarMain)
    }

    private fun createVideosList(){

    }

    private fun configureRecyclerVideos(){
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        adapter = AdapterRecyclerVideos(videosList)

        recycler_videos.layoutManager = layoutManager
        recycler_videos.setHasFixedSize(true)
        recycler_videos.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayout.VERTICAL))
        recycler_videos.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater

        menuInflater.inflate(R.menu.menu_main, menu)

        val menuItem : MenuItem? = menu?.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Procure um vídeo..."



        return true
    }
}