package com.android.guicelebrini.youtubeapp.model

data class Video(
    var title : String,
    var description : String,
    var date : String,
    var image: String,
    var videoId: String
    ){
    constructor(title: String, imageUrl: String) : this(title, "", "", imageUrl, "")
}
