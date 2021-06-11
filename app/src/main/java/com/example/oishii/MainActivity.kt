package com.example.oishii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.navigation.findNavController


class MainActivity : AppCompatActivity() {

    private lateinit var backArrow: ImageView
    private lateinit var burgerMenu: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        backArrow = findViewById(R.id.back_arrow)
        burgerMenu = findViewById(R.id.burger_menu)

        setButtonClickListeners()

    }

    private fun setButtonClickListeners() {
        backArrow.setOnClickListener {
            onBackPressed()
        }

        burgerMenu.setOnClickListener {
            findNavController(R.id.nav_host_fragment).navigate(R.id.burgerMenuFragment)
        }
    }

}