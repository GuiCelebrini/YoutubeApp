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
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.guicelebrini.youtubeapp.R
import com.android.guicelebrini.youtubeapp.adapter.AdapterRecyclerVideos
import com.android.guicelebrini.youtubeapp.api.YoutubeService
import com.android.guicelebrini.youtubeapp.helper.YoutubeInfos
import com.android.guicelebrini.youtubeapp.model.Item
import com.android.guicelebrini.youtubeapp.model.SearchResult
import com.android.guicelebrini.youtubeapp.model.Video
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : AdapterRecyclerVideos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureToolbar()

        createVideosList()

    }

    private fun configureToolbar(){
        var toolbarMain : Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbarMain)
    }

    private fun createVideosList(){
        val youtubeService = YoutubeService.create()

        youtubeService.getSearchResult("snippet", "viewCount", "20", YoutubeInfos.API_KEY, YoutubeInfos.CHANNEL_ID)
            .enqueue(object : Callback<SearchResult>{
                override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                    if(response.isSuccessful){
                        val searchResult = response.body()
                        if (searchResult != null) {
                            Log.i("Resultado", searchResult.items[9].snippet.thumbnails.default.url)

                            var videosList = arrayListOf<Video>()

                            searchResult.items.forEach{ item: Item ->
                                val video = Video(
                                    item.snippet.title,
                                    item.snippet.description,
                                    item.snippet.publishedAt,
                                    item.snippet.thumbnails.high.url,
                                    item.id.videoId
                                )

                                videosList.add(video)
                            }

                            Log.i("Resultado", videosList.toString())
                            configureRecyclerVideos(videosList)
                        }
                    }
                }
                override fun onFailure(call: Call<SearchResult>, t: Throwable) {

                }

            })
    }

    private fun configureRecyclerVideos(videosList: ArrayList<Video>){
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        adapter = AdapterRecyclerVideos(videosList)
        adapter.setOnClickListener(object : AdapterRecyclerVideos.OnItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(applicationContext, "Você vai assistir o vídeo '${videosList.get(position).title}' agora", Toast.LENGTH_SHORT).show()
            }

        })

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