package com.sorianog.pokeviewer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sorianog.pokeviewer.R
import com.sorianog.pokeviewer.data.entity.PokeSprites
import com.sorianog.pokeviewer.data.entity.PokeType
import com.sorianog.pokeviewer.data.entity.PokeTypes
import com.sorianog.pokeviewer.data.entity.PokemonDetailResponse

@Composable
fun PokeDetailView(
    pokemonDetail: PokemonDetailResponse,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        PokeImages(
            pokemonDetail.sprites.frontDefault,
            pokemonDetail.sprites.backDefault
        )
        PokemonNameLabel(nameText = pokemonDetail.name)
        PokemonTypesLabel(pokeTypes = pokemonDetail.types)
    }
}

@Preview
@Composable
fun PokeDetailViewPreview() {
    val pokemonDetail = PokemonDetailResponse(
        id = 25,
        name = "pikachu",
        types = listOf(
            PokeTypes(
                type = PokeType(
                    name = "electric",
                    url = "https://pokeapi.co/api/v2/type/13/"
                )
            )
        ),
        sprites = PokeSprites(
            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            backDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png"
        )
    )
    PokeDetailView(pokemonDetail)
}

@Composable
fun PokeImages(
    frontImgUrl: String,
    backImgUrl: String
) {
    Row {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .weight(0.5f),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(frontImgUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_loading),
            error = painterResource(R.drawable.ic_no_image),
            contentScale = ContentScale.Fit,
            contentDescription = "Image for news article"
        )

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .weight(0.5f),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(backImgUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_loading),
            error = painterResource(R.drawable.ic_no_image),
            contentScale = ContentScale.Fit,
            contentDescription = "Image for news article"
        )
    }
}

@Composable
fun PokemonNameLabel(nameText: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        color = colorResource(R.color.poke_yellow),
        text = nameText.uppercase(),
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PokemonTypesLabel(pokeTypes: List<PokeTypes>) {
    val typesText = pokeTypes.map { it.type }.joinToString { it.name }
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        color = colorResource(R.color.poke_yellow),
        text = stringResource(R.string.poke_types, typesText),
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center
    )
}