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
import com.aulloaq.storesapp.data.response.StoreResponse
import com.aulloaq.storesapp.utils.OnBottomReached
import com.aulloaq.storesapp.viewmodel.StoreListViewModel

/*@Composable
fun StoreList(
    uiState: StoreListUiState,
    viewModel: StoreListViewModel
){

    var listStore: List<StoreResponse> = mutableListOf()

    val listState = rememberLazyListState()

    when (val navigation = uiState.storeResponseState) {
        is StoreResponseState.Idle -> Unit
        is StoreResponseState.StoreResponse -> {
            listStore = navigation.apiResponse.data!!
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (topBar, storeList, loading) = createRefs()
        TopAppBar(modifier = Modifier.constrainAs(topBar){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "StoreList")
            }

        }

        if (uiState.showLoading)
            CircularLoading(modifier = Modifier.constrainAs(loading){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })

        LazyColumn(
            modifier = Modifier.constrainAs(storeList){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(topBar.bottom)
            },
            state = listState,
            contentPadding = PaddingValues(24.dp)
        ){
            items(listStore.size){
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
                                listStore[it].attributes?.name?.let { it1 -> Text(text = it1)}
                                Text(text = " | ")
                                listStore[it].attributes?.code?.let { it1 -> Text(text = it1)}
                            }
                            listStore[it].attributes?.full_address?.let { it1 -> Text(text = it1) }
                        }
                    }




                }
            }

        }

        listState.OnBottomReached {
            // do on load more
            viewModel.showLoading()
        }


    }
}*/


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StoreList(
    uiState: StoreListUiState,
    viewModel: StoreListViewModel
){

    var listStore: List<StoreResponse> = mutableListOf()

    val listState = rememberLazyListState()

    when (val navigation = uiState.storeResponseState) {
        is StoreResponseState.Idle -> Unit
        is StoreResponseState.StoreResponse -> {
            listStore = navigation.apiResponse.data!!
        }
    }

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
            items(listStore.size){
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
                                listStore[it].attributes?.name?.let { it1 -> Text(text = it1)}
                                Text(text = " | ")
                                listStore[it].attributes?.code?.let { it1 -> Text(text = it1)}
                            }
                            listStore[it].attributes?.full_address?.let { it1 -> Text(text = it1) }
                        }
                    }

                }
            }
        }
    }

    OnBottomReached(listState = listState) {
        viewModel.showLoading()
    }
}