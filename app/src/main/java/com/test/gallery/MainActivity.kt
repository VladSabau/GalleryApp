package com.test.gallery

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.test.gallery.gallery.GalleryActivity
import timber.log.Timber

class MainActivity : BaseActivity() {

    companion object {
        const val PICK_IMAGE = 100
        const val PICK_VIDEO = 101
    }

    private var paths: ArrayList<String> = ArrayList()
    private lateinit var btnAddVideo: Button
    private lateinit var btnAddImage: Button
    private lateinit var btnShowGallery: Button

    //todo: add presenter, move logic there

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddVideo = findViewById(R.id.btnAddVideo)
        btnAddImage = findViewById(R.id.btnAddImage)
        btnShowGallery = findViewById(R.id.btnShowGallery)

        this.btnAddVideo.setOnClickListener { onAddVideoClick() }
        this.btnAddImage.setOnClickListener { onAddImageClick() }
        this.btnShowGallery.setOnClickListener { showGallery() }
    }

    fun onAddVideoClick() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, PICK_VIDEO)
    }

    fun onAddImageClick() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_VIDEO) {
            data?.data?.let {
                val selectedVideoPath = getVideoPath(it)
                Timber.d("pick video")
                selectedVideoPath?.let { path -> paths.add(path) }
            }
        }
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            data?.data?.let {
                val selectedImagePath = getImagePath(it)
                Timber.d("pick image")
                selectedImagePath?.let { path -> paths.add(path) }
            }
        }
    }

    fun getVideoPath(uri: Uri): String? {
        //todo: find another way to take video/picture
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, null)
        return if (cursor != null) {
            val index: Int = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(index)
        } else null
    }

    fun getImagePath(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, null)
        return if (cursor != null) {
            val index: Int = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(index)
        } else null
    }

    fun showGallery() {
        GalleryActivity.start(this, 0, paths)
    }
}