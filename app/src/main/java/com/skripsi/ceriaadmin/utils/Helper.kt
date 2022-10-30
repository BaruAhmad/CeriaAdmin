package com.skripsi.ceriaadmin.utils

import android.app.Activity
import android.graphics.Rect
import android.os.Build
import android.view.MotionEvent
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun FullScreen(window: Window) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
    }
}

fun lighSystemBars(window: Window, isLightBars: Boolean = true, isLightNav: Boolean = true) {
    val wicc = WindowInsetsControllerCompat(window, window.decorView)
    wicc.isAppearanceLightStatusBars = isLightBars
    wicc.isAppearanceLightNavigationBars = isLightNav
}

fun hideSystemBars(window: Window) {
    WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.systemBars())
}

fun showSystemBars(window: Window) {
    WindowInsetsControllerCompat(window, window.decorView).show(WindowInsetsCompat.Type.systemBars())
}

fun Activity.hideKeyboard(event: MotionEvent) {
    if (event.action == MotionEvent.ACTION_DOWN) {
        val view = currentFocus
        if (view != null) {
            val outRect = Rect()
            view.getGlobalVisibleRect(outRect)
            if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                view.clearFocus()
                val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}