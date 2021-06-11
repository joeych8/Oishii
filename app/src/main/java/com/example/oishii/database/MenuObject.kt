package com.example.oishii.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class MenuObject(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val header: String,
    val contentDescription: String?,
    val allergensDescription: String?,
    val priceTag: Int,
    val addToCart: String?

)