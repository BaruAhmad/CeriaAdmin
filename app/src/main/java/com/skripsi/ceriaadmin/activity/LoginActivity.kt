@file:Suppress("DEPRECATION")

package com.skripsi.ceriaadmin.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skripsi.ceriaadmin.R
import com.skripsi.ceriaadmin.api.*
import com.skripsi.ceriaadmin.api.request.LoginRequest
import com.skripsi.ceriaadmin.api.response.LoginResponse
import com.skripsi.ceriaadmin.databinding.ActivityLoginBinding
import com.skripsi.ceriaadmin.utils.*
import retrofit2.*

class LoginActivity : AppCompatActivity() {

    private lateinit var apiConfig: ApiConfig
    private lateinit var binding: ActivityLoginBinding
    private lateinit var checkInternet: CheckInternet
    private lateinit var s: PrefManager
    private var waktuKeluar = 0L
    val bottomSheet: BottomSheetDialogFragment = NoInternet()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lighSystemBars(window)
        cekInternet()
        apiConfig = ApiConfig()
        s = PrefManager(this)

        with(binding) {
            btnMasuk.setOnClickListener {
                if (username.text.toString().isEmpty() and password.text.toString().isEmpty()) {
                    username.setBackgroundResource(R.drawable.bg_form_error)
                    usernameError.visibility = View.VISIBLE
                    password.setBackgroundResource(R.drawable.bg_form_error)
                    passwordError.visibility = View.VISIBLE
                    return@setOnClickListener
                }
                if (username.text.toString().isEmpty() and password.text.toString().isNotEmpty()) {
                    username.setBackgroundResource(R.drawable.bg_form_error)
                    usernameError.visibility = View.VISIBLE
                    password.setBackgroundResource(R.drawable.bg_form)
                    passwordError.visibility = View.GONE
                    return@setOnClickListener
                }
                if (username.text.toString().isNotEmpty() and password.text.toString().isEmpty()) {
                    username.setBackgroundResource(R.drawable.bg_form)
                    usernameError.visibility = View.GONE
                    password.setBackgroundResource(R.drawable.bg_form_error)
                    passwordError.visibility = View.VISIBLE
                    return@setOnClickListener
                }
                username.setBackgroundResource(R.drawable.bg_form)
                usernameError.visibility = View.GONE
                password.setBackgroundResource(R.drawable.bg_form)
                passwordError.visibility = View.GONE
                loadingBar.visibility = View.VISIBLE
                btnMasuk.isEnabled = false
                btnMasuk.setText("")
                masuk()
            }
        }
    }

    private fun masuk() {
        apiConfig.getApiService(this).login(LoginRequest(binding.username.text.toString(), binding.password.text.toString())).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val respon = response.body()
                if (response.isSuccessful) {
                    binding.loadingBar.visibility = View.GONE
                    binding.btnMasuk.isEnabled = true
                    binding.btnMasuk.setText(R.string.login)
                    if (respon!!.token.isNotEmpty()) {
                        s.saveToken(respon.token)
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                        overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
                    }
                    else {
                        Toast.makeText(this@LoginActivity, "Nama pengguna atau kata sandi salah. Mohon periksa lagi.", Toast.LENGTH_LONG).show()
                    }
                }
                else {
                    binding.loadingBar.visibility = View.GONE
                    binding.btnMasuk.isEnabled = true
                    binding.btnMasuk.setText(R.string.login)
                    Toast.makeText(this@LoginActivity, "Terjadi kesalahan, silahkan coba lagi.", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.loadingBar.visibility = View.GONE
                binding.btnMasuk.isEnabled = true
                binding.btnMasuk.setText(R.string.login)
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
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

    override fun onStart() {
        super.onStart()
        if (s.fetchToken() != null) {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
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