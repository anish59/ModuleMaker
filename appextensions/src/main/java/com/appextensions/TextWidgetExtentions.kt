package com.appextensions

import android.annotation.SuppressLint
import android.text.Spannable
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

/**
 * this file will contain extensions for TextView and  EditText
 * */

fun EditText.isEmpty(): Boolean = TextUtils.isEmpty(this.text.toString().trim())

fun TextView.isEmpty(): Boolean = TextUtils.isEmpty(this.text.toString().trim())

fun TextView.toText(): String = this.text.toString().trim()


//Extension method to set different color for substring TextView.
@SuppressLint("LogNotTimber")
fun TextView.setColorOfSubstring(substring: String, color: Int) {

    try {

        val spannable = android.text.SpannableString(text)

        val start = text.indexOf(substring)
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, color)),
            start,
            start + substring.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        text = spannable

    } catch (e: Exception) {

        if (BuildConfig.DEBUG) {

            Log.e(
                "ViewExtensions",
                "exception in setColorOfSubstring, text=$text, substring=$substring", e
            )

        }

    }
}


