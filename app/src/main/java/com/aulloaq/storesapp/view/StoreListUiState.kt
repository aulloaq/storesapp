package com.aulloaq.storesapp.view

import com.aulloaq.storesapp.data.response.ApiResponse

data class StoreListUiState (
    val showLoading: Boolean = false,
    val apiErrorState: ApiErrorState = ApiErrorState.Idle,
    val storeResponseState: StoreResponseState = StoreResponseState.Idle
)

sealed class ApiErrorState{
    object Idle: ApiErrorState()
    data class ApiError(val string: String) : ApiErrorState()
}

sealed class StoreResponseState{
    object Idle: StoreResponseState()
    data class StoreResponse(val apiResponse: ApiResponse) : StoreResponseState()
}