package com.mansi.stackoverflowlisttask.data.repository

import android.content.res.AssetManager
import com.google.gson.Gson
import com.mansi.stackoverflowlisttask.data.local.ItemsDao
import com.mansi.stackoverflowlisttask.data.remote.ItemsRemoteDataSource
import com.mansi.stackoverflowlisttask.utils.performGetOperation
import javax.inject.Inject

class ItemsRepository @Inject constructor(
    private val remoteDataSource: ItemsRemoteDataSource,
    private val localDataSource: ItemsDao
) {
    fun getItems(order : String, sort : String, site : String) = performGetOperation(
        databaseQuery = { localDataSource.getAllItems() },
        networkCall = { remoteDataSource.getItems(order, sort, site) },
        saveCallResult = { localDataSource.insertAll(it.items) }
    )

}