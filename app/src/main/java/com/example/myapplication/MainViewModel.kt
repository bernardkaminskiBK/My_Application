package com.example.myapplication

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    lateinit var cTimer: CountDownTimer

    val counterNumber = MutableLiveData<Int>()
    val isBtnResetEnabled = MutableLiveData<Boolean>()
    val isBtnStartEnabled = MutableLiveData<Boolean>()
    val maxScore = MutableLiveData<Int>()
    val secondsLeft = MutableLiveData<Long>()

    private var isTimerStarted: Boolean = false

    init {
        counterNumber.value = 0
        maxScore.value = 0
        isBtnResetEnabled.value = false
        isBtnStartEnabled.value = true
        initCountDownTimer()
    }

    override fun onCleared() {
        super.onCleared()
        stopTimer()
    }

    private fun initCountDownTimer() {
        cTimer = object : CountDownTimer(30000, 1000) {

            override fun onFinish() {
                isBtnResetEnabled.value = true
                isBtnStartEnabled.value = false
                stopTimer()
                setMaxScore()
            }

            override fun onTick(millisUntilFinished: Long) {
                secondsLeft.value = ((millisUntilFinished / 1000))
            }
        }
    }

    private fun setMaxScore(){
        if(counterNumber.value as Int > maxScore.value as Int){
            maxScore.value = counterNumber.value
        }
    }

    fun btnClickClicked() {
        if (!isTimerStarted) {
            startTimer()
        }
        counterNumber.value = counterNumber.value?.plus(1)
    }

    fun restartButtonClicked() {
        stopTimer()
        isBtnStartEnabled.value = true
        counterNumber.value = 0
        secondsLeft.value = 30
    }

    private fun startTimer(){
        isBtnResetEnabled.value = false
        isTimerStarted = true
        cTimer.start()
    }

    private fun stopTimer(){
        isTimerStarted = false
        cTimer.cancel()
    }

}