package com.adhrox.gymplanner.ui.plandetail

import android.app.Dialog
import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
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
class DialogCountdownFragment(private val timeCountdown: Long): DialogFragment() {
    private var _binding: DialogCountdownBinding? = null
    private val binding get() = _binding!!
    private val planDetailViewModel: PlanDetailViewModel by viewModels()

    private var timeInSeconds = (timeCountdown/1000).toInt()

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

        initUIState()
        initListeners()
        initMedia()

        binding.pbTimer.max = timeInSeconds
        binding.pbTimer.progress = timeInSeconds

    }

    private fun initMedia() {
        mediaPlayer = MediaPlayer.create(context, R.raw.alarm)
    }

    private fun initListeners() {
        binding.btnStart.setOnClickListener { planDetailViewModel.startTimer() }
        binding.btnReset.setOnClickListener { planDetailViewModel.resetTimer() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                planDetailViewModel.timer.collect(){
                    formatCountdownText(it)
                }
            }
        }
    }

    private fun formatCountdownText(timeLeft: Long){
        val timeLeftInSeconds = timeLeft/1000
        val minutes = (timeLeftInSeconds)/60
        val seconds = (timeLeftInSeconds)%60
        val minuteFormatted = "%02d".format(minutes)
        val secondFormatted = "%02d".format(seconds)
        binding.tvMinutesCountDown.text = minuteFormatted
        binding.tvSecondsCountDown.text = secondFormatted
        binding.pbTimer.progress = (timeLeftInSeconds).toInt()

        Log.i("adhrox", "$timeLeftInSeconds")

        if (timeLeftInSeconds == 0L){
            playSound()
        }

    }

    private fun playSound(){
        mediaPlayer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mediaPlayer.release()
    }
}