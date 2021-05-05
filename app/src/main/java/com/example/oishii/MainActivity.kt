package com.example.oishii

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var backArrow: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        backArrow = findViewById(R.id.back_arrow)


    backArrow.setOnClickListener {
        onBackPressed()
    }


    }
}