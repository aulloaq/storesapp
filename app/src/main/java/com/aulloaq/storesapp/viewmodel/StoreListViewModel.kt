package com.aulloaq.storesapp.viewmodel

import androidx.lifecycle.ViewModel
import com.aulloaq.storesapp.domain.usecase.GetStoreListUseCase
import com.aulloaq.storesapp.view.StoreListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    private val getStoreListUseCase: GetStoreListUseCase,
    private val dispatcherIO: CoroutineDispatcher
) : ViewModel(){

    private val _uiState = MutableStateFlow(StoreListUiState())
    val uiState: StateFlow<StoreListUiState> get() = _uiState.asStateFlow()

}