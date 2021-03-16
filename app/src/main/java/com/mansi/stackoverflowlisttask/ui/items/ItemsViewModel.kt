package com.mansi.stackoverflowlisttask.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.mansi.stackoverflowlisttask.data.entities.Items
import com.mansi.stackoverflowlisttask.data.entities.ItemsList
import com.mansi.stackoverflowlisttask.data.repository.ItemsRepository
import com.mansi.stackoverflowlisttask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(repository: ItemsRepository) : ViewModel() {

    val itemsCall = repository.getItems("desc", "activity", "stackoverflow")

  /*  private val _id = MutableLiveData<ItemsList>()

//    val users: MutableLiveData<ItemsList>
//        get() = itemsCall.value
//
//    private val _character = _id.switchMap { id ->
//        repository.getCharacter(id)
//    }
//    val character: LiveData<Resource<Character>> = _character
//
//
//    fun start(id: Int) {
//        _id.value = id
//  }


    private val users: MutableLiveData<ItemsList> by lazy {
        MutableLiveData().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<List<User>> {
        return users
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users.
    }


    private val _items: MutableLiveData<ItemsList> = MutableLiveData()

    private val items = _items.switchMap { id ->
        repository.getItems("desc", "activity", "stackoverflow")
    }

    val itemList: LiveData<Resource<Items>> = items.value


    fun start() {
        _items.value = items
    }*/

}


