package com.aulloaq.storesapp.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aulloaq.storesapp.utils.OnBottomReached
import com.aulloaq.storesapp.viewmodel.StoreListViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun StoreList(
    uiState: StoreListUiState,
    viewModel: StoreListViewModel
){

    val listState = rememberLazyListState()
    val newsList = viewModel.newsList

    if (uiState.showLoading)
        CircularLoading(modifier = Modifier)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text("StoreList") }
            )
        }
    ) {
        LazyColumn(
            Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            state = listState
        ) {
            items(newsList.size){
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.medium,
                    elevation = 5.dp,
                    backgroundColor = MaterialTheme.colors.surface
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(Modifier.padding(8.dp)) {
                            Row {
                                newsList[it].attributes?.name?.let { it1 -> Text(text = it1)}
                                Text(text = " | ")
                                newsList[it].attributes?.code?.let { it1 -> Text(text = it1)}
                            }
                            newsList[it].attributes?.full_address?.let { it1 -> Text(text = it1) }
                        }
                    }

                }
            }
        }
    }

    OnBottomReached(listState = listState) {
        viewModel.loadItems()
    }
}