package com.example.oishii.menu

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.oishii.R

class CustomMenuView(context: Context): ConstraintLayout(context) {

            private val dishTitle: TextView
            private val contentDescription: TextView
            private val allergensDescription: TextView
            private val priceTag: TextView
            private val addToCart: TextView



    init {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_content_view, this)

        dishTitle = view.findViewById(R.id.dish_texView1)
        contentDescription = view.findViewById(R.id.content_description_textView1)
        allergensDescription = view.findViewById(R.id.allergens_textView1)
        priceTag = view.findViewById(R.id.price_textView1)
        addToCart = view.findViewById(R.id.addToCart_textView1) //TODO not yet implemented

    }

    //function to set text to linear layout in cardView
    fun setText(menu: MenuObject) {
        dishTitle.text = menu.header
        contentDescription.text = menu.contentDescription
        allergensDescription.text = menu.allergensDescription
        priceTag.text = menu.priceTag
    }




}
