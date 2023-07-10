package com.aulloaq.storesapp.data.response

data class ApiResponse(
    val data: List<StoreResponse>?,
    val links: LinkResponse?
)

data class StoreResponse (
    val id: String?,
    val type: String?,
    val attributes: AttributesResponse?
)

data class AttributesResponse(
    val name: String?,
    val code: String?,
    val full_address: String?
)

data class LinkResponse(
    val previous: String? = "",
    val next: String? = "",
    val first: String? = "",
    val last: String? = "",
    val self: String? = ""
)
