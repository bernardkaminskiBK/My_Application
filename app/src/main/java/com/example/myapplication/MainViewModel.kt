package com.example.myapplication

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private lateinit var cTimer: CountDownTimer

    private var isTimerStarted: Boolean = false

    private val _counterNumber = MutableLiveData<Int>()
    private val _isBtnStartEnabled = MutableLiveData<Boolean>()
    private val _maxScore = MutableLiveData<Int>()
    private val _secondsLeft = MutableLiveData<Long>()

    val counterNumber: LiveData<Int>
        get()  = _counterNumber

    val isBtnStartEnabled: LiveData<Boolean>
        get() = _isBtnStartEnabled

    val maxScore: LiveData<Int>
        get() = _maxScore

    val secondsLeft: LiveData<Long>
        get() = _secondsLeft

    init {
        _counterNumber.value = 0
        _maxScore.value = 0
        _isBtnStartEnabled.value = true
        initCountDownTimer()
    }

    override fun onCleared() {
        stopTimer()
        super.onCleared()
    }

    private fun initCountDownTimer() {
        cTimer = object : CountDownTimer(30000, 1000) {
            override fun onFinish() {
                _isBtnStartEnabled.value = false
                stopTimer()
                setMaxScore()
            }

            override fun onTick(millisUntilFinished: Long) {
                _secondsLeft.value = ((millisUntilFinished / 1000))
            }
        }
    }

    private fun setMaxScore(){
        if(_counterNumber.value as Int > _maxScore.value as Int){
            _maxScore.value = _counterNumber.value
        }
    }

    fun btnClickClicked() {
        if (!isTimerStarted) {
            startTimer()
        }
        _counterNumber.value = _counterNumber.value?.plus(1)
    }

    fun restartButtonClicked() {
        stopTimer()
        _isBtnStartEnabled.value = true
        _counterNumber.value = 0
        _secondsLeft.value = 30
    }

    private fun startTimer(){
        isTimerStarted = true
        cTimer.start()
    }

    private fun stopTimer(){
        isTimerStarted = false
        cTimer.cancel()
    }

}