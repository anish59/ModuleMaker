package com.appextensions

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.applyDivider(context: Context, isLastDividerVisible: Boolean = false) {

    val ld = LineDividerItemDecoration(context, R.drawable.line_divider)
    ld.setLastDividerVisible(isLastDividerVisible)
    addItemDecoration(ld)

}

fun RecyclerView.applyTopDivider(context: Context) {

    val ld = LineDividerItemDecoration(context, R.drawable.line_divider)
    ld.isItTopDividerDecoration(true)
    addItemDecoration(ld)

}

class LineDividerItemDecoration(context: Context, dividerDrawable: Int) :
    RecyclerView.ItemDecoration() {

    private val mDivider: Drawable = ContextCompat.getDrawable(context, dividerDrawable)!!
    private var isLastDividerVisible: Boolean = false
    private var isItTopDivider: Boolean = false

    fun isItTopDividerDecoration(isItTopDivider: Boolean) {

        this.isItTopDivider = isItTopDivider
        this.isLastDividerVisible = true

    }

    fun setLastDividerVisible(lastDividerVisible: Boolean) {

        isLastDividerVisible = lastDividerVisible

    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        var childCount: Int = parent.childCount

        if (!isLastDividerVisible) {
            childCount -= 1
        }

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = if (isItTopDivider) {
                child.top + params.bottomMargin
            } else {
                child.bottom + params.bottomMargin
            }
            val bottom = top + mDivider.intrinsicHeight

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }

    }
}