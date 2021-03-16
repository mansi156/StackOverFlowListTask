package com.mansi.stackoverflowlisttask.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mansi.stackoverflowlisttask.data.entities.Items

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items")
    fun getAllItems() : LiveData<List<Items>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Items>)

}