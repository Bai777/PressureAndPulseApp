package com.example.pressureandpulseapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pressureandpulseapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startFragment(MainFragment())
    }

    private fun startFragment(mainFragment: MainFragment) {
       supportFragmentManager.beginTransaction().replace(
           binding.container.id, mainFragment
       ).commitNow()

    }
}