package com.example.oishii.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MenuItemDao {

    @Insert
    fun insertItem(itemToInsert: MenuObject)

    @Delete
    fun deleteItem(itemToDelete: MenuObject)

    @Query("SELECT header, priceTag FROM item_table") //TODO HELP HELP ! ! !
    fun getItems(): List<MenuObject>



}