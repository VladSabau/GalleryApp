package com.test.gallery.gallery

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Vlad Sabau on 12.11.20.
 */
abstract class BaseAdapter<T : DiffCallbackEquals, H : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<H>() {
    //todo this might be a leak, investigate
    var diffUtil: DiffUtilCallbackWrapper<T, H>? = DiffUtilCallbackWrapper(this)
}