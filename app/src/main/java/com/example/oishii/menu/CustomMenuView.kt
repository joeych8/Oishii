package com.example.oishii.menu

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import com.example.oishii.R
import com.example.oishii.database.MenuObject

class CustomMenuView(context: Context): LinearLayout(context) {

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
        addToCart = view.findViewById(R.id.addToCart_textView1)


    }

    //function to set text to linearLayout in cardView
    fun setMenuContentText(menu: MenuObject) {
        dishTitle.text = menu.header
        contentDescription.text = menu.contentDescription
        allergensDescription.text = menu.allergensDescription
        priceTag.text = menu.priceTag
        addToCart.text = menu.addToCart

    }




}
