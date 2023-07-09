package com.aulloaq.storesapp.domain.usecase

import com.aulloaq.storesapp.domain.model.Store
import com.aulloaq.storesapp.domain.repository.StoreRepository
import com.aulloaq.storesapp.utils.HandlerResult
import com.aulloaq.storesapp.utils.SuspendHandlerUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetStoreListUseCase @Inject constructor(
    private val repository: StoreRepository,
    dispatcher: CoroutineDispatcher
) : SuspendHandlerUseCase<List<Store>>(dispatcher) {

    override suspend fun execute(): HandlerResult<List<Store>> {
        return repository.getStoreList()
    }

}