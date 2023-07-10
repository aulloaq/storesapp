package com.aulloaq.storesapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.aulloaq.storesapp.ui.theme.StoresappTheme
import com.aulloaq.storesapp.viewmodel.StoreListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreListActivity : ComponentActivity() {

    private val viewModel: StoreListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val uiState by viewModel.uiState.collectAsState()

            when (val result = uiState.apiErrorState){
                is ApiErrorState.Idle -> Unit
                is ApiErrorState.ApiError -> {
                    showMessage(result.string)
                }
            }

            StoresappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StoreList(uiState = uiState, viewModel = viewModel)
                }
            }
        }
    }


    private fun showMessage(message: String){
        Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
    }
}