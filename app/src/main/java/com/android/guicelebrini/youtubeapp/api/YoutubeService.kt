package com.android.guicelebrini.youtubeapp.api

import com.android.guicelebrini.youtubeapp.helper.YoutubeInfos
import com.android.guicelebrini.youtubeapp.model.SearchResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/*

    --> building url

    https://www.googleapis.com/youtube/v3/
    search
    ?part=snippet
    &order=viewCount
    &maxResults=20
    &key=AIzaSyBOFLgDrtBEtp-wpw523mWEogXm52paLNM
    &channelId=UCKTlayjh47LQdeHGzaHWMfw

    --> complete url

    https://www.googleapis.com/youtube/v3/search?part=snippet&order=viewCount&maxResults=20&key=AIzaSyBOFLgDrtBEtp-wpw523mWEogXm52paLNM&channelId=UCKTlayjh47LQdeHGzaHWMfw

 */

interface YoutubeService {

    //with query it brings together and creates automatically the url
    @GET("search")
    fun getSearchResult(
        @Query("part") part: String,
        @Query("order") order: String,
        @Query("maxResults") maxResults: String,
        @Query("key") key: String,
        @Query("channelId") channelId: String,
        ) : Call<SearchResult>


    companion object{

        fun create() : YoutubeService {

            val retrofit = Retrofit.Builder().baseUrl(YoutubeInfos.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(YoutubeService::class.java)
        }


    }

}