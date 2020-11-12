package com.test.gallery.gallery

import com.test.gallery.BasePresenter

/**
 * Created by Vlad Sabau on 12.11.20.
 */
class GalleryPresenter : BasePresenter() {

    private lateinit var view: GalleryView

    fun attachView(view: GalleryView) {
        this.view = view
        if (view.checkWriteExternalPermission()) {
            retrievePathAndBuildUi()
        } else {
            view.grantPermission()
        }
    }

    fun onPermissionGranted() {
        retrievePathAndBuildUi()
    }

    private fun retrievePathAndBuildUi() {
        val mapper = GalleryMapper()
        val paths = view.getMediaPats()
        paths?.let {
            val result = mapper.transformToUi(it)
            view.init(result as ArrayList<GalleryUiModel>)
        }

    }
}