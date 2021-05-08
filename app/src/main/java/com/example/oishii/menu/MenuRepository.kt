package com.example.oishii.menu

import com.example.oishii.OishiiApplication
import com.example.oishii.database.AppDatabase
import com.example.oishii.database.MenuObject

/**Hent ut alle DB funksjonene som trengs i MenuFrag og legg inn her*/

class MenuRepository {


    val database =
        AppDatabase.getDatabase(OishiiApplication.application.applicationContext).menuItemDao()
    private val menuItemDao = database

    fun addItemToDB(item: MenuObject) {
        menuItemDao.insertItem(item)
    }


}