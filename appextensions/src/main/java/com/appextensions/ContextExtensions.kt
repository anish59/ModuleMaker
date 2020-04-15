package com.appextensions

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.math.roundToInt


fun Context.fireIntentWithData(intent: Intent, clearStack: Boolean = false) =
    (this as AppCompatActivity).fireIntentWithData(intent, clearStack)


fun Context.fireIntent(cls: Class<*>, clearStack: Boolean = false) =
    (this as AppCompatActivity).fireIntent(cls, clearStack)


fun Context.showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()


public fun Context.dpToPx(dp: Int): Int {

    val displayMetrics: DisplayMetrics = resources.displayMetrics
    return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()

}


public fun Context.pxToDp(px: Int): Int {

    val displayMetrics: DisplayMetrics = resources.displayMetrics
    return (px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()

}


//dialogs

fun Context.showAlertMessage(title: String, message: String) {
    if (title.isNotBlank() && message.isNotBlank()) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }.show()
    }
}


@SuppressLint("LogNotTimber")
fun Context.openDatePicker(listener: DateSelectionListener) {

    val cal = Calendar.getInstance()

    val dateDialog =
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->

            if (BuildConfig.DEBUG) {
                Log.e("selectedDate", "$date-${month + 1}-$year") //as month starts from 0
            }

            listener.onDateSelected(year, month + 1, date)

        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))

    val minDate = Calendar.getInstance()
    dateDialog.datePicker.minDate = minDate.timeInMillis
    dateDialog.show()
}


fun Context.openTimePicker(listener: TimeSelectionListener) {
    val currentTime = Calendar.getInstance()
    val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
    val currentMin = currentTime.get(Calendar.MINUTE)

    TimePickerDialog(
        this, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->

            listener.onTimeSelected(hourOfDay, minute)

        }, currentHour, currentMin, false
    ).show()
}


public interface DateSelectionListener {
    fun onDateSelected(year: Int, month: Int, date: Int)
}

public interface TimeSelectionListener {
    fun onTimeSelected(hour: Int, minute: Int)
}





