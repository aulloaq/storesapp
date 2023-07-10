package com.aulloaq.storesapp.view


data class StoreListUiState (
    val showLoading: Boolean = false,
    val apiErrorState: ApiErrorState = ApiErrorState.Idle,
)

sealed class ApiErrorState{
    object Idle: ApiErrorState()
    data class ApiError(val string: String) : ApiErrorState()
}
