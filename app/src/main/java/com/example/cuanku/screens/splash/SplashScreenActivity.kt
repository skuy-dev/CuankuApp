package com.example.cuanku.screens.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.databinding.ActivitySplashScreenBinding
import com.example.cuanku.helper.AppManager
import com.example.cuanku.screens.auth.LoginActivity
import com.example.cuanku.screens.dasboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    @Inject
    lateinit var getSession: AppManager

    override fun setLayout(inflater: LayoutInflater): ActivitySplashScreenBinding {
        return ActivitySplashScreenBinding.inflate(inflater)
    }

    override fun initialization() {
        loadSession()
    }

    override fun observeViewModel() {
//        TODO("Not yet implemented")
    }

    private fun loadSession() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (getSession.isLogin() != "") {
                startActivity(Intent(this, DashboardActivity::class.java))
            } else startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)

    }

}