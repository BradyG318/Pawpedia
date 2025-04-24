package edu.quinnipiac.ser210.pawpedia.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.quinnipiac.ser210.pawpedia.DogViewModel
import edu.quinnipiac.ser210.pawpedia.R
import edu.quinnipiac.ser210.pawpedia.SQL.Dog
import edu.quinnipiac.ser210.pawpedia.ui.theme.PawpediaTheme


val dogList: List<Dog> = listOf()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(size: Int, viewModel: DogViewModel,
               isDarkTheme: Boolean,
               onToggleTheme: () -> Unit) {
    val dogs by viewModel.dogs.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(size) {
        viewModel.loadDogBySize(size)
    }
    PawpediaTheme {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text(
                            text = "Pawpedia",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { println("Back Button Clicked") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back Button"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { println("Share Button Clicked") }) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Share Button"
                            )
                        }
                        IconButton(onClick = onToggleTheme) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Toggle Theme"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
        ) {innerPadding->(innerPadding)
            LazyColumn(
                modifier= Modifier
                    .offset(y=100.dp)
            ) {
                items(dogs) { dog ->
                    dogCard(dog, {println("Clicked on " +dog.breed_name)})
                }
            }
        }
    }
}

@Composable
fun dogCard(dog: Dog, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .border(width = 3.dp, color = Color.Black, shape = RectangleShape)
            .clickable{onClick()},
    ) {
        Image( //Dog PFP Img
            painterResource(dog.cover_photo_res_id),
            contentDescription = "Dog Pic",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
        Column(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Text( //Breed Name
                text = dog.breed_name,
                fontSize = 40.sp
            )
            Text(
                text = dog.tag_list,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview
@Composable
fun testDogCard() {
    val sampleDog = Dog(
    id=1,
    breed_name = "Shih Tzu",
    cover_photo_res_id = R.drawable.shihtzu, // make sure this image is in drawable
    tag_list = "toy, companion",
    size_index = 0,
    allergy_info = "Low allergen",
    average_weight = 12.0,
    description = "The Shih Tzu is a toy dog breed known for its long, silky coat and sweet personality."
    )
    dogCard(sampleDog, {println("Clicked on " + sampleDog.breed_name)})
}