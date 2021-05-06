package com.example.oishii.menu

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oishii.OishiiApplication
import com.example.oishii.database.AppDatabase
import com.example.oishii.database.MenuItemDao
import com.example.oishii.database.MenuObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {

    private val readAllData: List<MenuObject>
    private val repository: MenuRepository

    init {
        val menuItemDao = AppDatabase.getDatabase(OishiiApplication.application.applicationContext).menuItemDao()
        repository = MenuRepository(menuItemDao)
        readAllData = repository.readAllData
    }


    fun addItem(item: MenuObject){
        viewModelScope.launch(Dispatchers.IO){
            repository.addItem(item)

        }
    }


}