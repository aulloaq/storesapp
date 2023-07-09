package com.aulloaq.storesapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

            StoresappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}