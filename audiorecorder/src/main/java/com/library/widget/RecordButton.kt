package com.library.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.library.R

class RecordButton(ctx: Context, attributeSet: AttributeSet) :
    AppCompatButton(ctx, attributeSet) {

    internal var isRecording = false
/*
    var clicker = OnClickListener {
        text = when (isRecording) {
            true -> ctx.getString(R.string.stop)
            false -> ctx.getString(R.string.start)
        }
        isRecording = !isRecording
    }*/
/*
    init {
        text = ctx.getString(R.string.start)
        setOnClickListener(this)
    }*/


}