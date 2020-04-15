package com.appextensions

import android.view.View


fun View.makeVisible(
    visible: Boolean,
    useGone: Boolean = true
) { // if you want to use Invisible just pass useGone = false

    this.visibility = if (visible) View.VISIBLE else if (useGone) View.GONE else View.INVISIBLE

}



