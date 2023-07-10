package com.aulloaq.storesapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex

@Composable
fun CircularLoading(modifier: Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = 0.6f))
        .zIndex(1f)
        .clickable(
            indication = null, // disable ripple effect
            interactionSource = remember { MutableInteractionSource() },
            onClick = { }
        ),
        contentAlignment = Alignment.Center
    )
    {
        CircularProgressIndicator(
            modifier = modifier,
            color = MaterialTheme.colors.primary
        )
    }
}