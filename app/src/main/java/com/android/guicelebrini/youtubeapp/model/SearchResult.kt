package com.android.guicelebrini.youtubeapp.model

class SearchResult {

    var regionCode = ""
    lateinit var pageInfo : PageInfo
    var items = listOf<Item>()
}