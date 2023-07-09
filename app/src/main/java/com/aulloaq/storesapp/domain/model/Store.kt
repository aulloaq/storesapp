package com.aulloaq.storesapp.domain.model

import com.google.gson.annotations.SerializedName

data class Store(
    val name: String?,
    val code: String?,
    @SerializedName("full_address")
    val address: String?
)
