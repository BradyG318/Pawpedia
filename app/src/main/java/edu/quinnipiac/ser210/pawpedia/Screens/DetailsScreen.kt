package edu.quinnipiac.ser210.pawpedia.Screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.quinnipiac.ser210.pawpedia.R
import edu.quinnipiac.ser210.pawpedia.SQL.Dog


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    dog: Dog,
    onToggleTheme: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pawpedia") },
                navigationIcon = {
                    IconButton(onClick = { println("Back Button Clicked") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val shareText = makeShareText(dog)
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, "Look at this dog breed I found!")
                            putExtra(Intent.EXTRA_TEXT, shareText)
                        }
                        context.startActivity(
                            Intent.createChooser(shareIntent, "Share Via")
                        )
                    }) {
                        Icon(Icons.Filled.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = onToggleTheme) {
                        Icon(Icons.Default.Settings, contentDescription = "Toggle Theme")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Image
            Image(
                painter = painterResource(id = dog.cover_photo_res_id),
                contentDescription = "${dog.breed_name} photo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Breed Name
            Text(
                text = dog.breed_name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = dog.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Other Info
            Text("Tags: ${dog.tag_list}")
            Text("Size Index: ${dog.size_index}")
            Text("Average Weight: ${dog.average_weight} lbs")
            Text("Allergy Info: ${dog.allergy_info}")
        }
    }
}
fun makeShareText(dog: Dog): String =
    buildString {
        appendLine("Breed: ${dog.breed_name}")
        appendLine()
        appendLine(dog.description)
        appendLine()
        appendLine("Tags: ${dog.tag_list}")
        appendLine("Size index: ${dog.size_index}")
        appendLine("Average weight: ${dog.average_weight} lbs")
        appendLine("Allergy info: ${dog.allergy_info}")
    }

//@Preview(showBackground = true)
//@Composable
//fun PreviewDetailsScreen() {
//    val sampleDog = Dog(
//        breed_name = "Saint Bernard",
//        cover_photo_res_id = R.drawable.saintbernard, // Replace with an actual drawable for preview to work
//        tag_list = "working, giant, rescue",
//        size_index = 2,
//        allergy_info = "High allergen",
//        average_weight = 140.0,
//        description = "Saint Bernards are large, powerful dogs originally bred for rescue work in the Swiss Alps, known for their loyalty and gentle temperament."
//    )
//
//    DetailsScreen(dog = sampleDog)
//}