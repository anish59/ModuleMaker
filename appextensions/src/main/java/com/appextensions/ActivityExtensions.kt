package com.appextensions

import android.content.Intent
import android.os.Build
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

fun AppCompatActivity.fireIntentWithData(intent: Intent, clearStack: Boolean = false) {

    if (clearStack) {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    } else {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    startActivity(intent)

    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}


fun AppCompatActivity.fireIntent(cls: Class<*>, clearStack: Boolean = false) {

    val i = Intent(this, cls)

    if (clearStack) {
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    } else {
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    startActivity(i)

    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}

fun AppCompatActivity.closeScreen() {
    finish()
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}

fun AppCompatActivity.setFullScreen() {

    requestWindowFeature(Window.FEATURE_NO_TITLE)

    this.window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )

}

fun AppCompatActivity.setStatusBarColor(colorResId: Int) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, colorResId)
    }

}

fun AppCompatActivity.hideKeyBoard() {

    val imm = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus

    if (view != null) {

        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }

}


