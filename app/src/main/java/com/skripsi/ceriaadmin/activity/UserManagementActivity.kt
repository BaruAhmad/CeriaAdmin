@file:Suppress("DEPRECATION")

package com.skripsi.ceriaadmin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skripsi.ceriaadmin.R
import com.skripsi.ceriaadmin.adapter.UserAdapter
import com.skripsi.ceriaadmin.databinding.ActivityUserManagementBinding
import com.skripsi.ceriaadmin.model.User
import com.skripsi.ceriaadmin.utils.*

class UserManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserManagementBinding
    private lateinit var checkInternet: CheckInternet
    private lateinit var adapter: UserAdapter
    private lateinit var user: List<User>
    val bottomSheet: BottomSheetDialogFragment = NoInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lighSystemBars(window, false, true)
        cekInternet()
        userList()
        adapter = UserAdapter(user)
        binding.rvUser.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.rvUser.setHasFixedSize(true)
        binding.rvUser.adapter = adapter

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.addUser.setOnClickListener {
            AddUserBottom().show(supportFragmentManager, "AddUser")
        }
    }

    private fun userList() {
        user = listOf(
            User(R.drawable.foto_profile, "User Pertama", "180155201001"),
            User(R.drawable.foto_profile, "User Kedua", "180155201002"),
            User(R.drawable.foto_profile, "User Ketiga", "180155201003"),
            User(R.drawable.foto_profile, "User Keempat", "180155201004"),
            User(R.drawable.foto_profile, "User Kelima", "180155201005"),
            User(R.drawable.foto_profile, "User Keenam", "180155201006"),
            User(R.drawable.foto_profile, "User Ketujuh", "180155201007"),
            User(R.drawable.foto_profile, "User Kedelapan", "180155201008"),
            User(R.drawable.foto_profile, "User Kesembilan", "180155201009"),
            User(R.drawable.foto_profile, "User Kesepuluh", "180155201010"),
            User(R.drawable.foto_profile, "User Kesebelas", "180155201011"),
            User(R.drawable.foto_profile, "User Keduabelas", "180155201012"),
            User(R.drawable.foto_profile, "User Ketigabelas", "180155201013"),
            User(R.drawable.foto_profile, "User Keempatbelas", "180155201014"),
            User(R.drawable.foto_profile, "User Kelimabelas", "180155201015"),
        )
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