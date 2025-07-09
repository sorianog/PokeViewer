package com.sorianog.pokeviewer.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sorianog.pokeviewer.ui.Greeting

@Composable
fun HomeScreen() {
    Greeting(
        name = "Pikachu",
        modifier = Modifier.padding(0.dp)
    )
}