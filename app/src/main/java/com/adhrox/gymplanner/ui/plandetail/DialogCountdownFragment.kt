package com.adhrox.gymplanner.ui.plandetail

import android.app.Dialog
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.databinding.DialogCountdownBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DialogCountdownFragment(private val timeCountdown: Long) : DialogFragment() {

    private var _binding: DialogCountdownBinding? = null
    private val binding get() = _binding!!

    private val planDetailViewModel: PlanDetailViewModel by viewModels()

    private var timeInSeconds = (timeCountdown / 1000).toInt()

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogCountdownBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        planDetailViewModel.setTimer(timeCountdown)

        initUI()

        return builder.create()
    }

    private fun initUI() {
        binding.pbTimer.max = timeInSeconds
        binding.pbTimer.progress = timeInSeconds

        if (timeCountdown == 0L) {
            binding.fabPlayPause.isEnabled = false
            binding.fabReset.isEnabled = false
        }

        initUIState()
        initListeners()
        initMedia()
    }

    private fun initMedia() {
        mediaPlayer = MediaPlayer.create(context, R.raw.alarm)
    }

    private fun initListeners() {
        binding.fabPlayPause.setOnClickListener { startTimer() }
        binding.fabReset.setOnClickListener { resetTimer() }
        binding.fabClose.setOnClickListener { dismiss() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                planDetailViewModel.timer.collect() {
                    formatCountdownText(it)
                }
            }
        }
    }

    private fun startTimer() {
        planDetailViewModel.startTimer()
        val timerStatus = planDetailViewModel.getTimerStatus()

        binding.fabPlayPause.isSelected = timerStatus
    }

    private fun resetTimer() {
        planDetailViewModel.resetTimer()

        binding.fabPlayPause.isSelected = false
    }

    private fun formatCountdownText(timeLeft: Long) {
        val timeLeftInSeconds = timeLeft / 1000
        val minutes = (timeLeftInSeconds) / 60
        val seconds = (timeLeftInSeconds) % 60
        val minuteFormatted = "%02d".format(minutes)
        val secondFormatted = "%02d".format(seconds)
        binding.tvMinutesCountDown.text = minuteFormatted
        binding.tvSecondsCountDown.text = secondFormatted
        binding.pbTimer.progress = (timeLeftInSeconds).toInt()

        if (timeLeftInSeconds == 0L && timeCountdown != 0L) {
            playSound()
            binding.fabPlayPause.isSelected = false
        }

    }

    private fun playSound() {
        mediaPlayer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.pbTimer.max = 0
        binding.pbTimer.progress = 0
        _binding = null
        mediaPlayer.release()
    }
}