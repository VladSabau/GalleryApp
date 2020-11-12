package com.test.gallery.gallery

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Vlad Sabau on 12.11.20.
 */
class DiffUtilCallback<T : DiffCallbackEquals> constructor(private var oldList: MutableList<T>,
                                                           private var newList: MutableList<T>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].diffCallbackEquals(newList[newItemPosition])
}