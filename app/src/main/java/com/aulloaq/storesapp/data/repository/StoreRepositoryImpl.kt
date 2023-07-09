package com.aulloaq.storesapp.data.repository

import com.aulloaq.storesapp.AppConfig
import com.aulloaq.storesapp.data.api.StoreApi
import com.aulloaq.storesapp.domain.model.Store
import com.aulloaq.storesapp.domain.repository.StoreRepository
import com.aulloaq.storesapp.domain.usecase.GetStoreListUseCase
import com.aulloaq.storesapp.utils.HandlerResult
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeApi: StoreApi
) : StoreRepository {
    override suspend fun getStoreList(params: GetStoreListUseCase.GetStoreListParams): HandlerResult<List<Store>> {
        return try {
            val result = storeApi.getStoreList("Bearer ${AppConfig.getApiToken()}", AppConfig.getCompanyId(), params.perPage, params.page)
            val response = result.body()

            if (result.isSuccessful && !response.isNullOrEmpty()) {
                HandlerResult.Success(response)
            } else {
                HandlerResult.Failure(Exception("${result.code()} ${result.message()}"))
            }


        } catch (exception: Exception){
            HandlerResult.Failure(exception)
        }
    }
}