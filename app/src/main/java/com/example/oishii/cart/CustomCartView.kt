package com.example.oishii.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.oishii.R
import com.example.oishii.database.MenuObject

class CustomCartView(context: Context) : LinearLayout(context) {

    private val itemTitle: TextView
    private val priceTagCart: TextView?
    val minusTv: TextView


    init {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_content_view, this)

        itemTitle = view.findViewById(R.id.dish_texView1)
        priceTagCart = view.findViewById(R.id.price_textView_cart)
        minusTv = view.findViewById(R.id.minus_textView)
    }

    fun setCarthContentText(item: MenuObject) {
        itemTitle.text = item.header
        priceTagCart?.text = item.priceTag

    }












}
