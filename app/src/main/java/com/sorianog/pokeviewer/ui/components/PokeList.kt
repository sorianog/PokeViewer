package com.sorianog.pokeviewer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sorianog.pokeviewer.R
import com.sorianog.pokeviewer.data.entity.PokeResult

@Composable
fun PokeListItem(
    pokemon: PokeResult,
    onPokemonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(bottom = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { onPokemonClick(pokemon.name ?: "0") }
    ) {
        Row(
            modifier = modifier.padding(20.dp)
        ) {
            Text(
                modifier = modifier
                    .padding(start = 4.dp, end = 8.dp)
                    .weight(0.25f),
                fontStyle = FontStyle.Italic,
                fontSize = 24.sp,
                text = stringResource(
                    R.string.entry_num,
                    pokemon.url?.trimEnd('/')?.substringAfterLast('/') ?: "0"
                )
            )
            Text(
                modifier = modifier.weight(0.75f),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = pokemon.name?.uppercase() ?: stringResource(R.string.no_name)
            )
        }
    }
}

@Composable
fun PokeList(
    pokemonList: List<PokeResult>,
    onPokemonClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(items = pokemonList, key = { pokemon -> pokemon.name.hashCode() }) { pokemon ->
            PokeListItem(pokemon, onPokemonClick)
        }
    }
}

@Preview
@Composable
fun PokeListPreview() {
    val pokemonList = listOf(
        PokeResult(name = "bulbasaur"),
        PokeResult(name = "ivysaur"),
        PokeResult(name = "venusaur")
    )
    PokeList(pokemonList) {}
}