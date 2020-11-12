package com.test.gallery.gallery

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.test.gallery.R

/**
 * Created by Vlad Sabau on 12.11.20.
 */
class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val image: ImageView = view.findViewById(R.id.image)
    private val playerView: PlayerView = view.findViewById(R.id.playerView)

    private var player: SimpleExoPlayer? = null

    fun bind(context: Context, item: GalleryUiModel) {

        val path = item.path
        val extension: String = path.substring(path.lastIndexOf("."))

        if (extension.equals(".mp4", true)
            || extension.equals(".avi", true)
            || extension.equals(".mkv", true)) {

            image.visibility = View.GONE
            initializePlayer(context, path)

        } else {
            playerView.visibility = View.GONE
            image.visibility = View.VISIBLE
            Glide.with(context)
                .load(path)
                .fitCenter()
                .into(image)
        }
    }

    private fun initializePlayer(context: Context, path: String) {
        if (player == null) {
            player = SimpleExoPlayer.Builder(context, DefaultRenderersFactory(context)).build()
            playerView.player = player
            player?.playWhenReady = true
        }
        val mediaSource = buildMediaSource(context, path)
        player?.prepare(mediaSource, true, false)
        playerView.visibility = View.VISIBLE
    }

    fun onViewHolderRecycled() {
        player?.release()
        playerView.visibility = View.GONE
    }

    private fun buildMediaSource(context: Context, path: String): MediaSource {
        return ProgressiveMediaSource.Factory(
            DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "Gallery"))
        ).createMediaSource(Uri.parse(path))
    }
}