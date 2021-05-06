package com.example.oishii.menu

import androidx.lifecycle.LiveData
import com.example.oishii.database.MenuItemDao
import com.example.oishii.database.MenuObject

class MenuRepository(private val menuItemDao: MenuItemDao) {

    val readAllData: List<MenuObject> = menuItemDao.getItems()

    fun addItem(item: MenuObject){
        menuItemDao.insertItem(item)
    }


}