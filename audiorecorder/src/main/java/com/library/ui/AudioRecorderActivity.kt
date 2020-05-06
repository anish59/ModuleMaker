package com.library.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import com.gun0912.tedpermission.PermissionListener
import com.library.BR
import com.library.R
import com.library.databinding.ActivityAudioRecorderBinding
import com.library.extensions.fireIntentWithData
import com.library.extensions.setPermission
import com.library.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_audio_recorder.*
import java.util.*


class AudioRecorderActivity :
    BaseActivity<ActivityAudioRecorderBinding, RecorderActivityViewModel>(),
    RecorderActivityViewModel.AudioProgressListener {

    private val mViewModel = RecorderActivityViewModel(this, this)

    companion object {
        fun openAudioRecorder(context: Context, intent: Intent? = null) {
            if (intent != null) {
                context.fireIntentWithData(intent, false)
            } else {
                context.fireIntentWithData(
                    Intent(context, AudioRecorderActivity::class.java),
                    false
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = mViewModel

        setPermission(
            this,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
            ), object : PermissionListener {
                override fun onPermissionGranted() {

                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

                }
            }
        )


    }

    override fun getViewModel(): RecorderActivityViewModel {
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_audio_recorder
    }

    override fun initObserver() {
        // do nothing
    }

    override fun showAudioProgress(player: MediaPlayer) {
        val duration: Int = player.duration
        val amountToUpdate: Long = (duration / 100).toLong()
        val mTimer = Timer()

        /*  seekAudio.max = (player.duration) /100
            mTimer.schedule(object : TimerTask() {
                override fun run() {

                    runOnUiThread {
                        if (amountToUpdate * binding.seekAudio.progress < duration) {
                            var p: Int = binding.seekAudio.progress
                            p += 1
                            binding.seekAudio.progress = p
                        }
                    }

                }

            }, amountToUpdate)*/

        player.start()

        seekAudio.max = (player.duration) / 100
        val mHandler = Handler()
        runOnUiThread(object : Runnable {
            override fun run() {
                if (player.isPlaying) {
                    val mCurrentPosition: Int = player.currentPosition / 100
                    seekAudio.progress = mCurrentPosition
                    mHandler.postDelayed(this, 100)
                }
            }
        })

    }


}
