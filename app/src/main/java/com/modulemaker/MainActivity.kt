package com.modulemaker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carowlers.helper.AppLogger
import com.library.ui.AudioRecorderActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppLogger.e("Hi")
        AppLogger.e(User("Anish", "9876543210"))

        txt.setOnClickListener {

            AudioRecorderActivity.openAudioRecorder(this)

        }
    }
}
