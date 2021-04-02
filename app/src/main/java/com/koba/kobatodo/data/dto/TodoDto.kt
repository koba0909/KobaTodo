package com.koba.kobatodo.data.dto

import com.google.gson.annotations.SerializedName

data class TodoDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("description")
    val description: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("date")
    val date: String?
)