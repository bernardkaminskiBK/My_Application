package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

// test source for train to transform from extension to data binding
open class MainActivity : AppCompatActivity() {

    var counterNumber: Int = 0
    var isTimerStarted: Boolean = false
    var maxScore: Int = 0

    override fun onSaveInstanceState(outState: Bundle) {

        outState.putInt("counter", counterNumber)
        outState.putInt("maxScore", getHighestScore())
        outState.putString("timerText", countDownTextView.text.toString())
        outState.putBoolean("isTimerStarted", isTimerStarted)

        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState != null) {
            counterNumber = savedInstanceState.getInt("counter")
            maxScore = savedInstanceState.getInt("maxScore")
            isTimerStarted = savedInstanceState.getBoolean("isTimerStarted")

            highScoreResultTextView.text = maxScore.toString()
            counterTextView.text = counterNumber.toString()

            val remainingTimeText = savedInstanceState.getString("timerText")
            if(remainingTimeText != null && remainingTimeText.contains("s")){
                val timeText = remainingTimeText.split(" ")[0]
                startTimer(timeText.toLong() * 1000)
            }
        }

        btnClick.setOnClickListener {
            if (!isTimerStarted) {
                startTimer(30000)
                isTimerStarted = true
            }
            counterNumber++
            counterTextView.setText(counterNumber.toString())
        }

        btnResetCounter.setOnClickListener {
            counterTextView.setText("0")
            countDownTextView.text = "30 s"
            btnClick.isEnabled = true
            isTimerStarted = false
            maxScore = getHighestScore()
            if (maxScore < counterNumber) {
                highScoreResultTextView.text = counterNumber.toString()
            }
            counterNumber = 0
        }
    }

    private fun startTimer(millisInFuture: Long) {
        val cTimer = object : CountDownTimer(millisInFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countDownTextView.text = ((millisUntilFinished / 1000).toString() + " s")
                btnResetCounter.isEnabled = false
            }

            override fun onFinish() {
                btnClick.isEnabled = false
                btnResetCounter.isEnabled = true
                this.cancel()
            }
        }
        cTimer.start()
    }

    private fun getHighestScore(): Int {
        val textView = findViewById<TextView>(R.id.highScoreResultTextView).text.toString()
        return textView.toInt()
    }

}