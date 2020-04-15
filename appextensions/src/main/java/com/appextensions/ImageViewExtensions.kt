package com.appextensions

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.TintAwareDrawable

fun ImageView.tintSrc(@ColorRes colorRes: Int) {

    val drawable = DrawableCompat.wrap(drawable)

    DrawableCompat.setTint(drawable, ContextCompat.getColor(context, colorRes))
    setImageDrawable(drawable)

    if (drawable is TintAwareDrawable) invalidate() // Because in this case setImageDrawable will not call invalidate()

}

