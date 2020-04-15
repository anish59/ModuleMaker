package com.modulemaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carowlers.helper.AppLogger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppLogger.e("Hi")
        AppLogger.e(User("Anish", "9876543210"))
    }
}
