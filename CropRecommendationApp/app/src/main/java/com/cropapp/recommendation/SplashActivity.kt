package com.cropapp.recommendation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.cropapp.recommendation.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Animate logo
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        binding.ivLogo.startAnimation(fadeIn)

        val slideUp = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        binding.tvAppName.startAnimation(slideUp)
        binding.tvTagline.startAnimation(slideUp)

        // Navigate to MainActivity after delay
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2500)
    }
}
