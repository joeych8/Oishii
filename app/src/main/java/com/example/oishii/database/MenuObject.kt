package com.example.oishii.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
class MenuObject(
    @PrimaryKey(autoGenerate = true) //TODO
    val header: String,
    val contentDescription: String?,
    val allergensDescription: String?,
    val priceTag: String,
    val addToCart: String
)