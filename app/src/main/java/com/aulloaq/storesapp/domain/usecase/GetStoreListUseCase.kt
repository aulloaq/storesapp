package com.aulloaq.storesapp.domain.usecase

import com.aulloaq.storesapp.data.response.ApiResponse
import com.aulloaq.storesapp.di.CoroutineDispatcherIO
import com.aulloaq.storesapp.domain.model.Store
import com.aulloaq.storesapp.domain.repository.StoreRepository
import com.aulloaq.storesapp.utils.HandlerResult
import com.aulloaq.storesapp.utils.SuspendHandlerUseCaseParams
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetStoreListUseCase @Inject constructor(
    private val repository: StoreRepository,
    @CoroutineDispatcherIO
    dispatcher: CoroutineDispatcher
) : SuspendHandlerUseCaseParams<GetStoreListUseCase.GetStoreListParams, ApiResponse>(dispatcher) {

    data class GetStoreListParams(val perPage: Int = 20, val page: Int = 1)

    override suspend fun execute(params: GetStoreListParams): HandlerResult<ApiResponse> {
        return repository.getStoreList(params)
    }


}
