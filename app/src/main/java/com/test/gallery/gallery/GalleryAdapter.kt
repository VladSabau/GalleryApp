package com.test.gallery.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.gallery.R


/**
 * Created by Vlad Sabau on 12.11.20.
 */
class GalleryAdapter constructor(private val context: Context): BaseAdapter<GalleryUiModel, GalleryViewHolder>() {

    private var mediaFiles = ArrayList<GalleryUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(context)
        return GalleryViewHolder(inflater.inflate(R.layout.gallery_item, parent, false) as ViewGroup)
    }

    override fun getItemCount(): Int {
        return mediaFiles.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(context, mediaFiles[position])
    }

    fun modelChange(galleryFiles: ArrayList<GalleryUiModel>) {
        diffUtil?.notifyDataSetChanged(this.mediaFiles, galleryFiles)
    }

    override fun onViewRecycled(holder: GalleryViewHolder) {
        super.onViewRecycled(holder)
        holder.onViewHolderRecycled()
    }
}

