package com.library.extensions

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator


fun View.blinkAnimation(isStart: Boolean, duration: Long = 500) {
    if (isStart) {
        val animation: Animation = AlphaAnimation(
            1.toFloat(),
            0.toFloat()
        ) //to change visibility from visible to invisible

        animation.duration = duration

        animation.interpolator = LinearInterpolator()
        animation.repeatCount = Animation.INFINITE //repeating indefinitely

        animation.repeatMode = Animation.REVERSE //animation will start from end point once ended.

        startAnimation(animation) //to start animation
    } else {
        animation.cancel()
    }
}

