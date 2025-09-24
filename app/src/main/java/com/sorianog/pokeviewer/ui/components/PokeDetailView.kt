package com.sorianog.pokeviewer.ui.components

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sorianog.pokeviewer.R
import com.sorianog.pokeviewer.data.entity.PokeCries
import com.sorianog.pokeviewer.data.entity.PokeSprites
import com.sorianog.pokeviewer.data.entity.PokeType
import com.sorianog.pokeviewer.data.entity.PokeTypes
import com.sorianog.pokeviewer.data.entity.PokemonDetailResponse

@Composable
fun PokeDetailView(
    pokemonDetail: PokemonDetailResponse
) {
    PokeImages(
        pokemonDetail.sprites.frontDefault,
        pokemonDetail.sprites.backDefault
    )
    PokemonNameLabel(nameText = pokemonDetail.name)
    PokemonTypesLabel(pokeTypes = pokemonDetail.types)
    if (pokemonDetail.cries.latest.isNotEmpty()) {
        PlayPokemonVoiceButton(voiceUri = pokemonDetail.cries.latest)
    } else if (pokemonDetail.cries.legacy.isNotEmpty()) {
        PlayPokemonVoiceButton(voiceUri = pokemonDetail.cries.legacy)
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
        ),
        cries =
            PokeCries(
                latest = "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/25.ogg",
                legacy = "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/legacy/25.ogg"
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
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PlayPokemonVoiceButton(voiceUri: String) {
    Button(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.poke_blue)
        ),
        onClick = {
            MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(voiceUri)
                prepare()
                start()
            }
        }
    ) {
        Text(
            text = stringResource(R.string.play_voice),
            color = colorResource(R.color.poke_yellow),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PokemonFlavorTextView(
    flavorText: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.poke_dark_yellow),
        ),
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Column(
            modifier = modifier.padding(20.dp)
        ) {
            Text(
                text = stringResource(R.string.about_pokemon),
                color = colorResource(R.color.poke_blue),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(bottom = 10.dp)
            )
            Text(
                text = flavorText,
                color = colorResource(R.color.poke_blue),
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun PokemonFlavorTextPreview() {
    PokemonFlavorTextView("When several of\\nthese POKéMON\\ngather, their\\felectricity could\\nbuild and cause\\nlightning storms.")
}