package com.example.oishii.cart

import com.example.oishii.OishiiApplication
import com.example.oishii.database.AppDatabase
import com.example.oishii.database.MenuObject

class CartRepository {

    val database = AppDatabase.getDatabase(OishiiApplication.application.applicationContext)
    private val cartItemDao = database.menuItemDao()

    fun fetchAllItemsFromDB(): List<MenuObject> {
        return cartItemDao.getItems()
    }

    fun deleteItemFromDB(itemToDelete: MenuObject) {
        cartItemDao.deleteItem(itemToDelete)
    }

    fun deleteAllItemsFromDB() {
        cartItemDao.deleteAllItems()
    }

}