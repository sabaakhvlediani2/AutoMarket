package com.example.automarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Main entry point — hosts fragment container and loads ShopFragment on first launch...
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load ShopFragment only on first creation (not on configuration change)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ShopFragment())
                .commit()
        }
    }
}
