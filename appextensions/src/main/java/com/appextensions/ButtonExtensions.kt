package com.appextensions

import android.widget.Button


fun Button.disableButton() {

    isEnabled = false
    alpha = 0.7f

}

fun Button.enableButton() {

    isEnabled = true
    alpha = 1.0f

}