package com.mansi.stackoverflowlisttask.data.entities

data class RequestBody(
    var order : String = "desc",
    var sort : String = "activity",
    var site : String = "stackoverflow"
)