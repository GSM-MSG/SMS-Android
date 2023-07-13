package com.sms.presentation.main.ui.base

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback


abstract class BaseActivity(backPressedEnabled: Boolean = true) : ComponentActivity() {

    private var doubleBackToExitPressedOnce = false
    private var backPressedTimestamp = 0L
    private val onBackPressedCallback = object : OnBackPressedCallback(backPressedEnabled) {
        override fun handleOnBackPressed() {
            controlTheStackWhenBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        viewSetting()
        observeEvent()
    }

    abstract fun viewSetting()

    abstract fun observeEvent()

    protected fun controlTheStackWhenBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (doubleBackToExitPressedOnce && currentTime - backPressedTimestamp <= 2000) {
            finishAffinity()
        } else {
            doubleBackToExitPressedOnce = true
            backPressedTimestamp = currentTime
            Toast.makeText(this, "한 번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }

    protected fun setSoftInputMode(isType: String = "NOTHING") {
        window.setSoftInputMode(
            when (isType) {
                "PAN" -> WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                "RESIZE" -> WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                else -> WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
            }
        )
    }
}