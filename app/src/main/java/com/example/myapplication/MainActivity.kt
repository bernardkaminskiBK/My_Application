package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding

open class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        updateData()

        binding.btnClick.setOnClickListener {
            mainViewModel.btnClickClicked()
            updateData()
        }

        binding.btnResetCounter.setOnClickListener {
            var highestScore = binding.highScoreResultTextView.text.toString().toInt()
            mainViewModel.maxScore = mainViewModel.highestScoreResult(highestScore)
            mainViewModel.counterNumber = 0
            updateData()
        }
    }

    private fun updateData() {
        binding.highScoreResultTextView.text = mainViewModel.maxScore.toString()
        binding.counterTextView.text = mainViewModel.counterNumber.toString()

        binding.countDownTextView.text = "30 s"
        binding.btnClick.isEnabled = true
        mainViewModel.isTimerStarted = false
    }

//    private fun startTimer(millisInFuture: Long) {
//        val cTimer = object : CountDownTimer(millisInFuture, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                binding.countDownTextView.text = ((millisUntilFinished / 1000).toString() + " s")
//                binding.btnResetCounter.isEnabled = false
//            }
//
//            override fun onFinish() {
//                binding.btnClick.isEnabled = false
//                binding.btnResetCounter.isEnabled = true
//                this.cancel()
//            }
//        }
//        cTimer.start()
//    }

}