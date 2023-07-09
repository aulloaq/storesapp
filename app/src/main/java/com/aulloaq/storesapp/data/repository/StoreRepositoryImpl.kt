package com.aulloaq.storesapp.data.repository

import com.aulloaq.storesapp.data.api.StoreApi
import com.aulloaq.storesapp.domain.model.Store
import com.aulloaq.storesapp.domain.repository.StoreRepository
import com.aulloaq.storesapp.utils.HandlerResult
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeApi: StoreApi
) : StoreRepository {
    override suspend fun getStoreList(): HandlerResult<List<Store>> {
        return try {
            val result = storeApi.getStoreList()
        }
    }
}