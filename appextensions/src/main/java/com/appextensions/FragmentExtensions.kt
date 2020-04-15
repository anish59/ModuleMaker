package com.appextensions

import android.content.Intent
import androidx.fragment.app.Fragment

fun Fragment.fireIntentWithData(intent: Intent, clearStack: Boolean = false) =
    activity!!.fireIntentWithData(intent, clearStack)


fun Fragment.fireIntent(cls: Class<*>, clearStack: Boolean = false) =
    activity!!.fireIntent(cls, clearStack)
