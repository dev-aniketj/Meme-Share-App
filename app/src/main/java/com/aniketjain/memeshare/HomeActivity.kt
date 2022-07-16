package com.aniketjain.memeshare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aniketjain.memeshare.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Setup of the Binding View
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        onClicksListeners()

    }

    private fun onClicksListeners() {
        binding.shareBtn.setOnClickListener { }
        binding.nextBtn.setOnClickListener { }
    }

    private fun setUpImage() {

    }
}