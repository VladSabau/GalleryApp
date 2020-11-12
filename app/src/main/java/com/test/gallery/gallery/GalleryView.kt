package com.test.gallery.gallery

/**
 * Created by Vlad Sabau on 12.11.20.
 */
interface GalleryView {

    fun getPosition(): Int?
    fun getMediaPats(): ArrayList<String>?
    fun checkWriteExternalPermission(): Boolean
    fun grantPermission()
    fun init(list: ArrayList<GalleryUiModel>)
}