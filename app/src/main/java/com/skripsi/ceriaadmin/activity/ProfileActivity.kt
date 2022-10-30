@file:Suppress("DEPRECATION")

package com.skripsi.ceriaadmin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skripsi.ceriaadmin.R
import com.skripsi.ceriaadmin.databinding.ActivityProfileBinding
import com.skripsi.ceriaadmin.utils.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var checkInternet: CheckInternet
    val bottomSheet: BottomSheetDialogFragment = NoInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lighSystemBars(window, false, true)
        cekInternet()

        binding.btnBack.setOnClickListener {
            onBackPressed()
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

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        hideKeyboard(ev)
        return super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}