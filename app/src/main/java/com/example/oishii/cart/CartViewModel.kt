package com.example.oishii.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oishii.database.MenuObject
import com.example.oishii.menu.MenuRepository
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


    fun deleteItem(item: MenuObject) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteItemFromDB(item)
        }
    }

    fun deleteAllItems() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAllItemsFromDB()
        }
    }

//    private val repository = MenuRepository()
//    val cartListLiveData: MutableLiveData<List<MenuObject>> = MutableLiveData()
//
//
//    fun fetchAllItems(){
//        CoroutineScope(Dispatchers.IO).launch {
//            val list = repository.fetchAllItemsFromDB()
//
//            cartListLiveData.postValue(list)
//        }
//    }


}