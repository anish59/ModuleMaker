package com.library.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.library.R
import com.library.extensions.logE

class CustomButton : AppCompatButton, View.OnClickListener {
    internal var isRecording = false

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init()
    }

    constructor(context: Context?) : super(context) {
        init()
    }

    private fun init() {
        setOnClickListener(this)
        logE()
    }

    override fun onClick(v: View) {
        text = when (isRecording) {
            true -> v.context.getString(R.string.stop)
            false -> v.context.getString(R.string.start)
        }
        isRecording = !isRecording
        logE("hey2")
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        logE("hey2")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
}