package com.aniketjain.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.aniketjain.memeshare.databinding.ActivityHomeBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var currentImageURL: String? = null

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
        binding.shareBtn.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Hey, Checkout this cool meme I got from Reddit $currentImageURL"
                )
            }
            startActivity(Intent.createChooser(intent, "Share this meme using..."))
        }
        binding.nextBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            loadMeme()
        }
    }

    private fun loadMeme() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"
        // Request a JSON response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                currentImageURL = response.getString("url")
                Glide.with(this).load(currentImageURL).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.image.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        binding.image.visibility = View.VISIBLE
                        return false
                    }
                }).into(binding.image)
            },
            {
                Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show()
            }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
}