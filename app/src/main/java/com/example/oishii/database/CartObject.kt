package com.example.oishii.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CartObject (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val header: String,
    val priceTag: String

    )