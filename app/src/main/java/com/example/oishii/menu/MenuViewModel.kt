package com.example.oishii.menu

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oishii.OishiiApplication
import com.example.oishii.database.AppDatabase
import com.example.oishii.database.MenuItemDao
import com.example.oishii.database.MenuObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


    /**Lag ny/nye funksjoner som inneholder funksjonene fra repo for å bruke i fragment (lag i viewModel til der de skal brukes)*/

class MenuViewModel : ViewModel() {

    private val repository = MenuRepository()
    //val cartListLiveData: MutableLiveData<List<MenuObject>> = MutableLiveData()
    //LiveData


    fun addItem(item: MenuObject){
        CoroutineScope(Dispatchers.IO).launch {
           repository.addItemToDB(item)
        }
    }
}