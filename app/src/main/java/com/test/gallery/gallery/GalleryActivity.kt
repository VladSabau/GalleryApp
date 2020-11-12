package com.test.gallery.gallery

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.test.gallery.BaseActivity
import com.test.gallery.R


/**
 * Created by Vlad Sabau on 12.11.20.
 */
class GalleryActivity : BaseActivity(), GalleryView {
    companion object {
        private const val PERMISSION_REQUEST = 123
        private const val POSITION = "position"
        private const val MEDIA = "media"

        @JvmStatic
        fun start(context: Context, position: Int, mediaPaths: ArrayList<String>) {
            val intent = Intent(context, GalleryActivity::class.java)
            intent.putExtra(POSITION, position)
            intent.putExtra(MEDIA, mediaPaths)
            context.startActivity(intent)
        }
    }

    private var viewPager: ViewPager2? = null
    private val presenter = GalleryPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gallery)

        viewPager = findViewById(R.id.galleryViewPager)
        presenter.attachView(this)
    }

    override fun getPosition(): Int? {
        return intent?.extras?.getInt(POSITION)
    }

    override fun getMediaPats(): java.util.ArrayList<String>? {
        return intent?.extras?.getStringArrayList(MEDIA)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            presenter.onPermissionGranted()
        }
    }

    override fun init(list: ArrayList<GalleryUiModel>) {
        val galleryPagerAdapter = GalleryAdapter(this)
        galleryPagerAdapter.modelChange(list)
        viewPager?.adapter = galleryPagerAdapter
        viewPager?.offscreenPageLimit = 3
    }

    override fun checkWriteExternalPermission(): Boolean {
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        val res = checkCallingOrSelfPermission(permission)
        return res == PackageManager.PERMISSION_GRANTED
    }

    override fun grantPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                PERMISSION_REQUEST
            )
        }
    }
}