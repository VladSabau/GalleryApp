package com.test.gallery.gallery

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Vlad Sabau on 12.11.20.
 */
class DiffUtilCallbackWrapper<T : DiffCallbackEquals, VH : RecyclerView.ViewHolder> constructor(private val adapter: RecyclerView.Adapter<VH>) {

    fun notifyDataSetChanged(oldList: ArrayList<T>, newList: ArrayList<T>) {
        if (oldList.isNotEmpty()) {
            val diffResult = DiffUtil.calculateDiff(
                DiffUtilCallback(
                    oldList,
                    newList
                ), true)

            oldList.clear()
            oldList.addAll(newList)

            diffResult.dispatchUpdatesTo(adapter)
        } else {
            oldList.addAll(newList)
            adapter.notifyDataSetChanged()
        }
    }
}