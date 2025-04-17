package edu.quinnipiac.ser210.pawpedia

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.quinnipiac.ser210.pawpedia.ui.theme.PawpediaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    PawpediaTheme {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor= MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor=MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text(
                            text = "Pawpedia",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {/*Put back navigation here*/}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back Button"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {/*Add share functionality here*/}) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Share Button"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                    )
            },

        ) {innerPadding->(innerPadding)
            Column(
                modifier = Modifier
                    .offset(y=100.dp)
            ) {
                sizeCard("Large Dogs", R.drawable.largedog)
                sizeCard("Medium Dogs", R.drawable.mediumdog)
                sizeCard("Small Dogs", R.drawable.smoldog)
            }
        }
    }
}

@Composable
fun sizeCard(dog:String, imgID:Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 3.dp, color = Color.Black, shape = RectangleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(imgID),
            contentDescription = "Size" + dog,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RectangleShape),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
        Text(
//            modifier = Modifier
//                .
            text = dog,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}