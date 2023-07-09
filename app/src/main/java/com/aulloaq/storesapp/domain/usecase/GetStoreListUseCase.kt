package com.aulloaq.storesapp.domain.usecase

import com.aulloaq.storesapp.domain.model.Store
import com.aulloaq.storesapp.domain.repository.StoreRepository
import com.aulloaq.storesapp.utils.HandlerResult
import com.aulloaq.storesapp.utils.SuspendHandlerUseCaseParams
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetStoreListUseCase @Inject constructor(
    private val repository: StoreRepository,
    dispatcher: CoroutineDispatcher
) : SuspendHandlerUseCaseParams<GetStoreListUseCase.GetStoreListParams, List<Store>>(dispatcher) {

    data class GetStoreListParams(val perPage: Int = 10, val page: Int = 1)

    override suspend fun execute(params: GetStoreListParams): HandlerResult<List<Store>> {
        return repository.getStoreList(params)
    }


}