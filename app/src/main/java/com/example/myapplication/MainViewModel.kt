package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var counterNumber: Int = 0
    var isTimerStarted: Boolean = false
    var maxScore: Int = 0

    fun btnClickClicked() {
//        if (!isTimerStarted) {
//            startTimer(30000)
//            isTimerStarted = true
//        }
        counterNumber++
    }

    fun highestScoreResult(maxScore: Int): Int {
        return if (maxScore < counterNumber) {
            counterNumber.toString().toInt()
        } else {
            maxScore.toString().toInt()
        }
    }
}