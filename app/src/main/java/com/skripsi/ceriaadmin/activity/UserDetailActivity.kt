package com.skripsi.ceriaadmin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skripsi.ceriaadmin.databinding.ActivityUserDetailBinding
import com.skripsi.ceriaadmin.utils.CheckInternet
import com.skripsi.ceriaadmin.utils.NoInternet
import com.skripsi.ceriaadmin.utils.lighSystemBars

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var checkInternet: CheckInternet
    val bottomSheet: BottomSheetDialogFragment = NoInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lighSystemBars(window, false, true)
        cekInternet()

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
}