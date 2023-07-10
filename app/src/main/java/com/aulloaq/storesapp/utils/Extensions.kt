package com.aulloaq.storesapp.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import com.aulloaq.storesapp.domain.usecase.GetStoreListUseCase
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun OnBottomReached(
    listState: LazyListState,
    buffer: Int = 2,
    onLoad: () -> Unit
){
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItemsNumber - buffer)
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .collect {
                onLoad()
            }
    }
}