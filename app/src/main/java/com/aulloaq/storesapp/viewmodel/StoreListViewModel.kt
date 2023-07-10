package com.aulloaq.storesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aulloaq.storesapp.data.response.ApiResponse
import com.aulloaq.storesapp.di.CoroutineDispatcherIO
import com.aulloaq.storesapp.domain.usecase.GetStoreListUseCase
import com.aulloaq.storesapp.utils.HandlerResult
import com.aulloaq.storesapp.view.ApiErrorState
import com.aulloaq.storesapp.view.StoreListUiState
import com.aulloaq.storesapp.view.StoreResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    private val getStoreListUseCase: GetStoreListUseCase,
    @CoroutineDispatcherIO
    private val dispatcherIO: CoroutineDispatcher
) : ViewModel(){

    private val _uiState = MutableStateFlow(StoreListUiState())
    val uiState: StateFlow<StoreListUiState> get() = _uiState.asStateFlow()

    fun getStoreList() {
        showLoading()
        viewModelScope.launch(dispatcherIO) {
            when (val result = getStoreListUseCase.invoke(GetStoreListUseCase.GetStoreListParams())) {
                is HandlerResult.Success -> {
                    setStoreResponse(result.value)
                }
                is HandlerResult.Failure -> {
                    result.exception.message?.let { setApiErrorState(it) }
                }

            }

        }
    }

    private fun setStoreResponse(apiResponse: ApiResponse){
        _uiState.update {
            it.copy(
                showLoading = false,
                storeResponseState = StoreResponseState.StoreResponse(apiResponse = apiResponse)
            )
        }
    }

    fun showLoading(showLoading: Boolean = true) {
        _uiState.update {
            it.copy(
                showLoading = showLoading
            )
        }
    }

    private fun setApiErrorState(message: String){
        _uiState.update {
            it.copy(
                showLoading = false,
                apiErrorState = ApiErrorState.ApiError(message)
            )
        }
    }

}