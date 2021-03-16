package com.mansi.stackoverflowlisttask.ui.items

import androidx.lifecycle.ViewModel
import com.mansi.stackoverflowlisttask.data.repository.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(repository: ItemsRepository) : ViewModel() {

    val itemsCall = repository.getItems("desc", "activity", "stackoverflow")

}


