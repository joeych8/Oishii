package com.example.oishii.cart

import com.example.oishii.OishiiApplication
import com.example.oishii.database.AppDatabase
import com.example.oishii.database.MenuObject

class CartRepository {

    val database = AppDatabase.getDatabase(OishiiApplication.application.applicationContext).menuItemDao()
    private val CartItemDao = database

    fun fetchAllItemsFromDB(): List<MenuObject>{
        return CartItemDao.getItems()
    }

    fun deleteItemFromDB(itemToDelete: MenuObject){
        CartItemDao.deleteItem(itemToDelete)
    }

    fun deleteAllItemsFromDB(){
        CartItemDao.deleteAllItems()
    }

}