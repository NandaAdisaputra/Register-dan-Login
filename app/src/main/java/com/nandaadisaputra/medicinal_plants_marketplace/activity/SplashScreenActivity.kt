package com.nandaadisaputra.medicinal_plants_marketplace.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.nandaadisaputra.medicinal_plants_marketplace.R
import org.jetbrains.anko.startActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity<LoginActivity>()
            finish()

        },4000)
    }
}
