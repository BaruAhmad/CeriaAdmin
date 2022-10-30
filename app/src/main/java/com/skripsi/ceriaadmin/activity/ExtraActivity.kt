package com.skripsi.ceriaadmin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.skripsi.ceriaadmin.R
import com.skripsi.ceriaadmin.adapter.ViewPagerAdapter
import com.skripsi.ceriaadmin.databinding.ActivityExtraBinding
import com.skripsi.ceriaadmin.utils.*

class ExtraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtraBinding
    private lateinit var checkInternet: CheckInternet
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    val bottomSheet: BottomSheetDialogFragment = NoInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExtraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lighSystemBars(window, false, true)
        cekInternet()
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        with(binding) {
            back.setOnClickListener {
                onBackPressed()
            }

            viewPager.adapter = viewPagerAdapter

            TabLayoutMediator(tabPager, viewPager) {tab, postion ->
                when(postion) {
                    0 -> tab.setText(R.string.kuis)
                    1 -> tab.setText(R.string.puzzle)
                }
            }.attach()
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

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}