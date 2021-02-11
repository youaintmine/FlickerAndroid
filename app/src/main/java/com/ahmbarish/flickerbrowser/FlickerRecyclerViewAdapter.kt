package com.ahmbarish.flickerbrowser

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
//This is the view holder model
class FlickerImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    var title : TextView = view.findViewById(R.id.titleView)
}

class FlickerRecyclerViewAdapter(private var photoList: List<Photo>) : RecyclerView.Adapter<FlickerImageViewHolder>() {
    private val TAG = "FlickerRecyclerViewAdapt"

    fun loadNewData(newPhotos : List<Photo>) {
        photoList = newPhotos
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int) : Photo? {
        return if(photoList.isNotEmpty()) photoList[position] else null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickerImageViewHolder {
        //called by layout manager when needing a new view
        Log.d(TAG,".onCreateViweHolder new view is requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickerImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickerImageViewHolder, position: Int) {
        //called by layout manager when it wants new data in an existing view

        val photoItem = photoList[position]
        Log.d(TAG,".onBindView Holder : ${photoItem.title} --> $position")
        Picasso.get().load(photoItem.image)
                .error(R.drawable.placeholder_48)
                .placeholder(R.drawable.placeholder_48)
                .into(holder.thumbnail)
        holder.title.text = photoItem.title

    }

    override fun getItemCount(): Int {
        Log.d(TAG, ".getItemCount called")
        return if (photoList.isNotEmpty()) photoList.size else 0
    }
}