package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding

open class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.counterNumber.observe(this, Observer {
            binding.counterTextView.text = it.toString()
        })

        mainViewModel.maxScore.observe(this, Observer {
            binding.highScoreResultTextView.text = it.toString()
        })

        mainViewModel.isBtnResetEnabled.observe(this, Observer {
            binding.btnResetCounter.isEnabled = it
        })

        mainViewModel.isBtnStartEnabled.observe(this, Observer {
            binding.btnClick.isEnabled = it
        })

        mainViewModel.secondsLeft.observe(this, Observer {
            binding.countDownTextView.text = getString(R.string.seconds_left, it)
        })

        binding.btnClick.setOnClickListener {
            mainViewModel.btnClickClicked()
        }

        binding.btnResetCounter.setOnClickListener {
            mainViewModel.restartButtonClicked()
        }
    }

}