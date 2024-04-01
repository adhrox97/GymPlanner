package com.adhrox.gymplanner.domain.model

import android.os.CountDownTimer
import javax.inject.Inject

class TimerModel @Inject constructor() {

    private var countDownTimer: CountDownTimer? = null
    private var timerRunning = false
    private var timeLeftInMillis: Long = 0
    private var timeSet: Long = 0
    private var savedTime: Long = 0
    var listener: TimerListener? = null

    fun startTimer() {
        if (!timerRunning) {
            countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    listener?.onTimerTick(millisUntilFinished)
                    timeLeftInMillis = millisUntilFinished
                }

                override fun onFinish() {
                    timerRunning = false
                    listener?.onTimerFinish()
                }
            }.start()
            timerRunning = true
        } else {
            pauseTimer()
        }
    }

    fun pauseTimer() {
        if (timerRunning) {
            countDownTimer?.cancel()
            timerRunning = false
            savedTime = timeLeftInMillis
        }
    }

    fun resetTimer() {
        countDownTimer?.cancel()
        timerRunning = false
        timeLeftInMillis = timeSet
        savedTime = 0
        listener?.onTimerReset()
    }

    fun resumeTimer() {
        if (!timerRunning) {
            timeLeftInMillis = savedTime
            startTimer()
        }
    }

    fun setTimeLeftInMillis(time: Long) {
        timeLeftInMillis = time
        timeSet = time
    }

    fun getTimeLeftInMillis(): Long {
        return timeSet
    }

    fun getTimerStatus(): Boolean {
        return timerRunning
    }

    interface TimerListener {
        fun onTimerTick(timeLeftInMillis: Long)
        fun onTimerFinish()
        fun onTimerReset()
    }

}