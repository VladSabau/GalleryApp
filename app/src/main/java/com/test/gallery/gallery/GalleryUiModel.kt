package com.test.gallery.gallery

/**
 * Created by Vlad Sabau on 12.11.20.
 */
class GalleryUiModel(
    var id: Int,
    var path: String
) : DiffCallbackEquals {
    override fun diffCallbackEquals(equals: Any): Boolean {
        if (this === equals) return true
        if (javaClass != equals.javaClass) return false
        val item = equals as GalleryUiModel
        return id == item.id &&
                path == item.path
    }
}