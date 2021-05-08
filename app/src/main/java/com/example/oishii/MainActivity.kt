package com.example.oishii

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var backArrow: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        backArrow = findViewById(R.id.back_arrow)


//TODO
//        backArrow.visibility = View.GONE


    backArrow.setOnClickListener {
        onBackPressed()
    }


    }
}