@file:Suppress("DEPRECATION")

package com.skripsi.ceriaadmin.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skripsi.ceriaadmin.R
import com.skripsi.ceriaadmin.databinding.ActivitySettingsBinding
import com.skripsi.ceriaadmin.utils.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var checkInternet: CheckInternet
    private lateinit var s: PrefManager
    val bottomSheet: BottomSheetDialogFragment = NoInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lighSystemBars(window, false, true)
        cekInternet()
        s = PrefManager(this)
        s.fetchToken()
//        binding.namaUser.text = s.getData()?.name
//        binding.nipUser.text = s.getData()?.nip

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.profile.setOnClickListener {
            startActivity(Intent(this@SettingsActivity, ProfileActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.gantiPassword.setOnClickListener {
            startActivity(Intent(this@SettingsActivity, PasswordActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.aturUser.setOnClickListener {
            startActivity(Intent(this@SettingsActivity, UserManagementActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.about.setOnClickListener {
            startActivity(Intent(this@SettingsActivity, AboutActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.exit.setOnClickListener {
            dialogExit()
        }
    }

    private fun dialogExit() {
        val dialog = Dialog(this)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_keluar)
        dialog.window?.attributes?.windowAnimations = R.style.DialogExit
        dialog.findViewById<Button>(R.id.yes_action).setOnClickListener {
            s.deleteToken()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finishAffinity()
            overridePendingTransition(0, 0)
        }
        dialog.findViewById<Button>(R.id.batal).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
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