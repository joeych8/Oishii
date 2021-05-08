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

    @Query("DELETE FROM item_table")
    fun deleteAllItems()

    @Query("SELECT * FROM item_table")
    fun getItems(): List<MenuObject>



}

