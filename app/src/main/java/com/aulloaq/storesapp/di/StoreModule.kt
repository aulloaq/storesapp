package com.aulloaq.storesapp.di

import android.content.Context
import com.aulloaq.storesapp.AppConfig
import com.aulloaq.storesapp.data.api.StoreApi
import com.aulloaq.storesapp.data.repository.StoreRepositoryImpl
import com.aulloaq.storesapp.domain.repository.StoreRepository
import com.aulloaq.storesapp.utils.ApiClientFactory
import com.aulloaq.storesapp.utils.ConnectionHelper
import com.aulloaq.storesapp.utils.ConnectionHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Provides
    @CoroutineDispatcherIO
    fun provideCoroutineDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
    @Provides
    @CoroutineDispatcherUI
    fun provideCoroutineDispatcherUI(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    fun provideConnectionHelper(@ApplicationContext context: Context): ConnectionHelper =
        ConnectionHelperImpl(context)

    @Provides
    fun provideStoreApi(connectionHelper: ConnectionHelper) : StoreApi =
        ApiClientFactory<StoreApi>().create(
            baseUrl = AppConfig.getBaseUrl(),
            connectionHelper = connectionHelper,
            tClass = StoreApi::class.java
        )
    @Provides
    @Singleton
    fun provideStoreRepository(storeApi: StoreApi) : StoreRepository{
        return StoreRepositoryImpl(storeApi = storeApi)
    }
}