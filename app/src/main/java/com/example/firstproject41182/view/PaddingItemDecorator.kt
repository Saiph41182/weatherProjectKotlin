package com.example.firstproject41182.view

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PaddingItemDecorator(private val context: Context) : RecyclerView.ItemDecoration() {

    private val mPadding: Int


    init {
        val displayMetrics = context.resources.displayMetrics;
        mPadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16F,displayMetrics).toInt()
    }


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val pos = parent.getChildAdapterPosition(view)

        when(pos) {
            RecyclerView.NO_POSITION -> return
            0 -> outRect.left = mPadding
            state.itemCount - 1 -> outRect.right = mPadding/2
        }
    }
}