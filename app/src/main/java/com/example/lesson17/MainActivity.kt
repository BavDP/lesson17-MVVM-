package com.example.lesson17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson17.fragments.LoginFragment
import com.example.lesson17.storage.StorageService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            StorageService.init(this)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, LoginFragment.newInstance())
                .commit()
        }
    }
}