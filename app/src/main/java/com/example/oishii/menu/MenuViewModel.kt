package com.example.oishii.menu

import androidx.lifecycle.ViewModel
import com.example.oishii.database.MenuObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch




class MenuViewModel : ViewModel() {

    private val repository = MenuRepository()


    fun addItem(item: MenuObject){
        CoroutineScope(Dispatchers.IO).launch {
           repository.addItemToDB(item)
        }
    }
}