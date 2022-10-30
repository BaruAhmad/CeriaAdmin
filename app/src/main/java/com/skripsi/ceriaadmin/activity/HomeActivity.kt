@file:Suppress("DEPRECATION")

package com.skripsi.ceriaadmin.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skripsi.ceriaadmin.R
import com.skripsi.ceriaadmin.adapter.CeritaAdapter
import com.skripsi.ceriaadmin.databinding.ActivityHomeBinding
import com.skripsi.ceriaadmin.model.Cerita
import com.skripsi.ceriaadmin.utils.*
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var checkInternet: CheckInternet
    private lateinit var adapter: CeritaAdapter
    private lateinit var cerita: List<Cerita>
    private lateinit var s: PrefManager
    private var waktuKeluar = 0L
    val bottomSheet: BottomSheetDialogFragment = NoInternet()
    val jam: Int = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cekInternet()
        greeting()
        ceritaList()
        setStatusBar()
        s = PrefManager(this)
        s.fetchToken()
        adapter = CeritaAdapter(cerita)
        binding.rvCerita.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.rvCerita.setHasFixedSize(true)
        binding.rvCerita.adapter = adapter

        binding.navExtra.setOnClickListener {
            startActivity(Intent(this@HomeActivity, ExtraActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.navBeranda.setOnClickListener {
            return@setOnClickListener
        }

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this@HomeActivity, AddCeritaActivity::class.java))
            overridePendingTransition(0, 0)
        }

        binding.btnSetting.setOnClickListener {
            startActivity(Intent(this@HomeActivity, SettingsActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    private fun ceritaList() {
        cerita = listOf(
            Cerita(R.drawable.book_cover, "Lorem Ipsum", "Lorem Ipsum"),
            Cerita(R.drawable.book_cover, "Lorem Ipsum", "Lorem Ipsum"),
            Cerita(R.drawable.book_cover, "Lorem Ipsum", "Lorem Ipsum"),
            Cerita(R.drawable.book_cover, "Lorem Ipsum", "Lorem Ipsum"),
            Cerita(R.drawable.book_cover, "Lorem Ipsum", "Lorem Ipsum")
        )
    }

    private fun greeting() {
        if (jam >= 0 && jam < 11) {
            binding.textGreeting.setText(R.string.pagi)
        }
        else if (jam >= 11 && jam < 15) {
            binding.textGreeting.setText(R.string.siang)
        }
        else if (jam >= 15 && jam < 18) {
            binding.textGreeting.setText(R.string.sore)
        }
        else if (jam >= 18 && jam < 24) {
            binding.textGreeting.setText(R.string.malam)
        }
    }

    private fun cekInternet() {
        checkInternet = CheckInternet(application)
        checkInternet.observe(this, {isConnected ->
            if (isConnected) {
                bottomSheet.dialog?.dismiss()
            }
            else {
                bottomSheet.show(supportFragmentManager, NoInternet.TAG)
            }
        })
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        hideKeyboard(ev)
        return super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        if (waktuKeluar + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        }
        else {
            Toast.makeText(applicationContext, R.string.toast_exit, Toast.LENGTH_SHORT).show()
        }
        waktuKeluar = System.currentTimeMillis()
    }
}