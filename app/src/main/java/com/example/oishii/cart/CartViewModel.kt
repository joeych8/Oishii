package com.example.oishii.cart

import androidx.lifecycle.ViewModel
import com.example.oishii.database.MenuObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val repository = CartRepository()


    fun fetchAllItems(callBack: (List<MenuObject>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = repository.fetchAllItemsFromDB()
            callBack(list)
        }
    }

    //TODO
    fun deleteItem(itemToDelete: MenuObject) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteItemFromDB(itemToDelete)

        }
    }

    fun deleteAllItems() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAllItemsFromDB()
        }
    }




}