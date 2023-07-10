package com.aulloaq.storesapp.domain.repository

import com.aulloaq.storesapp.data.response.ApiResponse
import com.aulloaq.storesapp.domain.model.Store
import com.aulloaq.storesapp.domain.usecase.GetStoreListUseCase
import com.aulloaq.storesapp.utils.HandlerResult

interface StoreRepository {
    suspend fun getStoreList(params: GetStoreListUseCase.GetStoreListParams): HandlerResult<ApiResponse>
}