package com.aulloaq.storesapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aulloaq.storesapp.data.response.LinkResponse
import com.aulloaq.storesapp.data.response.StoreResponse
import com.aulloaq.storesapp.di.CoroutineDispatcherIO
import com.aulloaq.storesapp.domain.usecase.GetStoreListUseCase
import com.aulloaq.storesapp.utils.HandlerResult
import com.aulloaq.storesapp.view.ApiErrorState
import com.aulloaq.storesapp.view.StoreListUiState
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

    private var links: LinkResponse? = null
    private val _uiState = MutableStateFlow(StoreListUiState())
    val uiState: StateFlow<StoreListUiState> get() = _uiState.asStateFlow()
    val newsList = mutableStateListOf<StoreResponse>()

    init {
        getStoreList()
    }

    private fun getStoreList(getStoreListParams: GetStoreListUseCase.GetStoreListParams = GetStoreListUseCase.GetStoreListParams()) {
        showLoading()
        viewModelScope.launch(dispatcherIO) {
            when (val result = getStoreListUseCase.invoke(getStoreListParams)) {
                is HandlerResult.Success -> {
                    links = result.value.links
                    newsList.addAll(result.value.data!!)
                    showLoading(false)
                }
                is HandlerResult.Failure -> {
                    result.exception.message?.let { setApiErrorState(it) }
                }

            }

        }
    }


    private fun showLoading(showLoading: Boolean = true) {
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

    fun loadItems(){
        if (links?.next != links?.last) {
            val indexPerPage = links?.next?.indexOf("=")
            lateinit var perPage: String
            lateinit var page: String
            lateinit var substr: String
            if (indexPerPage != null) {
                substr = links?.next?.substring(indexPerPage + 1).toString()
                perPage = substr.substring(0, 2)
                page = substr.substring(substr.indexOf("=") + 1)

                getStoreList(GetStoreListUseCase.GetStoreListParams(perPage = perPage.toInt(), page = page.toInt()))

            }
        }
    }

}