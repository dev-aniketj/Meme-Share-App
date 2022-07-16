package com.aniketjain.memeshare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.aniketjain.memeshare.databinding.ActivityHomeBinding
import com.aniketjain.roastytoasty.Toasty
import com.bumptech.glide.Glide

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Setup of the Binding View
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadMeme()
        onClicksListeners()

    }

    private fun onClicksListeners() {
        binding.shareBtn.setOnClickListener { }
        binding.nextBtn.setOnClickListener { loadMeme() }
    }

    private fun loadMeme() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        // Request a JSON response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val url = response.get("url")
                Glide.with(this).load(url).into(binding.image)
            },
            {
                Toasty.error(this, "Something went wrong.")
            }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    override fun onStart() {
        super.onStart()
    }
}