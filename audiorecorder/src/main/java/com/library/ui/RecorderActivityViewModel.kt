package com.library.ui

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.library.R
import com.library.extensions.blinkAnimation
import com.library.extensions.logE
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*


class RecorderActivityViewModel(
    var mContext: Context,
    var audioProgressListener: AudioProgressListener
) : ViewModel() {

    private var recorder: MediaRecorder? = null
    private var isRecording = false
    private var isPlaying = false
    private var fileName: String = ""
    private var player: MediaPlayer? = null


    fun recordOnClick(btnRecord: AppCompatButton, imgMic: ImageView) {
        when (isRecording) {
            true -> {
                btnRecord.text = mContext.getString(R.string.start)
                stopRecording()
                imgMic.blinkAnimation(false)
            }
            false -> {
                btnRecord.text = mContext.getString(R.string.stop)
                startRecording()
                imgMic.blinkAnimation(true)
            }
        }
        isRecording = !isRecording
    }

    fun playRecording(imgPlay: ImageView, seekAudio: SeekBar) {

        when (isPlaying) {
            true -> {
                imgPlay.setImageDrawable(
                    ContextCompat.getDrawable(
                        mContext,
                        R.drawable.ic_play_24dp
                    )
                )
                stopPlaying()
            }
            false -> {
                imgPlay.setImageDrawable(
                    ContextCompat.getDrawable(
                        mContext,
                        R.drawable.ic_stop_black_24dp
                    )
                )
                startPlaying(imgPlay, seekAudio)
            }
        }
        isPlaying = !isPlaying

    }

    private fun startRecording() {

        fileName = "${mContext.externalCacheDir?.absolutePath}/audioFile.3gp"
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e("LOG_TAG", "prepare() failed")
            }

            start()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    private fun startPlaying(imgPlay: ImageView, seekAudio: SeekBar) {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                logE(fileName, "filePath")
                prepare()
            } catch (e: IOException) {
                logE("prepare() failed")
            }
        }

        if (player != null) {
            audioProgressListener.showAudioProgress(player!!)
        }

        player?.setOnCompletionListener {
            GlobalScope.launch { // launch a new coroutine in background and continue
                delay(1000L)// 0.5 seconds
                stopPlaying()
                isPlaying = false
            }
            imgPlay.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_play_24dp))
        }
    }


    private fun stopPlaying() {
        player?.release()
        player = null
    }

    interface AudioProgressListener {
        fun showAudioProgress(player: MediaPlayer)
    }
}