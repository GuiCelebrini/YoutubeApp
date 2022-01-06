package com.android.guicelebrini.youtubeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.guicelebrini.youtubeapp.R
import com.android.guicelebrini.youtubeapp.model.Video
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_video_list.view.*

class AdapterRecyclerVideos (val videosList : ArrayList<Video>) : RecyclerView.Adapter<AdapterRecyclerVideos.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_video_list, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentVideo = videosList[position]
        holder.set(currentVideo)
    }

    override fun getItemCount(): Int {
        return videosList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun set(video : Video){
            itemView.text_title.text = video.title
            Picasso.get().load(video.image).into(itemView.image_video)
        }
    }
}