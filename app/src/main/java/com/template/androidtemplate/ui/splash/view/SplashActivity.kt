package com.template.androidtemplate.ui.splash.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.template.androidtemplate.ui.home.view.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_splash);
        observeLoggedInMode();

    }

    private fun observeLoggedInMode() {

        startActivity(Intent(this, HomeActivity::class.java))
        finish()

    }

}