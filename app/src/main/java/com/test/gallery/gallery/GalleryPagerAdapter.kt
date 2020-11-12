package com.test.gallery.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.gallery.R

/**
 * Created by Vlad Sabau on 12.11.20.
 */
class GalleryPagerAdapter constructor(private val context: Context): BaseAdapter<GalleryUiModel, MediaViewHolder>() {

    private var mediaFiles = ArrayList<GalleryUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val inflater = LayoutInflater.from(context)
        return MediaViewHolder(inflater.inflate(R.layout.gallery_image, parent, false) as ViewGroup)
    }

    override fun getItemCount(): Int {
        return mediaFiles.size
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(context, mediaFiles[position])
    }

    fun modelChange(galleryFiles: ArrayList<GalleryUiModel>) {
        diffUtil?.notifyDataSetChanged(this.mediaFiles, galleryFiles)
    }

}

class MediaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val galleryImage: ImageView = view.findViewById(R.id.galleryImage)

    fun bind(context: Context, item: GalleryUiModel) {
        Glide.with(context)
            .load(item.path)
            .fitCenter()
            .into(galleryImage)
    }

}