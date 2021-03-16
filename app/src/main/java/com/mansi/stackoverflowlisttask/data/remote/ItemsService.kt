package com.mansi.stackoverflowlisttask.data.remote

import com.mansi.stackoverflowlisttask.data.entities.ItemsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemsService {
    @GET("questions/no-answers")
    suspend fun getAllItems(@Query ("order")order : String,
                                 @Query ("sort")sort : String,
                                 @Query ("site")site : String) : Response<ItemsList>

}
