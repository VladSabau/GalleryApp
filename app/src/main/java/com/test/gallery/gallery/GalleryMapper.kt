package com.test.gallery.gallery

/**
 * Created by Vlad Sabau on 12.11.20.
 */
class GalleryMapper {

    fun transformToUi(paths: ArrayList<String>): List<GalleryUiModel> {
        return paths.map { GalleryUiModel(0, it) }
    }
}