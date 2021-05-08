package com.example.oishii.menu

import com.example.oishii.OishiiApplication
import com.example.oishii.database.AppDatabase
import com.example.oishii.database.MenuObject

    /**Hent ut alle DB funksjonene og legg inn her i repository*/

class MenuRepository {


    val database = AppDatabase.getDatabase(OishiiApplication.application.applicationContext).menuItemDao()
    private val menuItemDao = database

    fun addItemToDB(item: MenuObject){
        menuItemDao.insertItem(item)
    }

    fun fetchAllItemsFromDB(): List<MenuObject>{
        return menuItemDao.getItems()
    }

    fun deleteItemFromDB(itemToDelete: MenuObject){
        menuItemDao.deleteItem(itemToDelete)
    }



}