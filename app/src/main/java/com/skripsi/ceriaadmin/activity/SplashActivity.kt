@file:Suppress("DEPRECATION")

package com.skripsi.ceriaadmin.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.skripsi.ceriaadmin.R
import com.skripsi.ceriaadmin.databinding.ActivitySplashBinding
import com.skripsi.ceriaadmin.utils.*

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FullScreen(window)
        hideSystemBars(window)

        binding.noInternet.btnTry.setOnClickListener {
            binding.noInternet.loadingBar.visibility = View.VISIBLE
            binding.noInternet.btnTry.isEnabled = false
            binding.noInternet.btnTry.setText("")
            binding.noInternet.btnTry.setBackgroundResource(R.drawable.bg_btn)
            Handler().postDelayed({
                binding.noInternet.loadingBar.visibility = View.GONE
                binding.noInternet.btnTry.setText(R.string.try_again)
                cekInternet()
                binding.noInternet.btnTry.isEnabled = true
            }, 3500)
        }

        binding.activitySplash.startLayoutAnimation()
        binding.activitySplash.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                if (progress >= 0.42) {
                    binding.mitraApp.setImageResource(R.drawable.sds_bintan_light)
                }
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                cekInternet()
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }

        })
    }

    companion object {
        fun isNetworkStatusAvialable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }

    private fun cekInternet() {
        if (isNetworkStatusAvialable(applicationContext)) {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
            finish()
        }
        else {
            binding.activitySplash.visibility = View.GONE
            binding.noInternet.root.visibility = View.VISIBLE
            showSystemBars(window)
            lighSystemBars(window)
        }
    }
}