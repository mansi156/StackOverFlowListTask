package com.mansi.stackoverflowlisttask.data.remote

import javax.inject.Inject

class ItemsRemoteDataSource @Inject constructor(
    private val itemService: ItemsService
): BaseDataSource() {
    suspend fun getItems(order : String, sort : String, site : String) = getResult { itemService.getAllItems(order, sort, site) }

}