package edu.quinnipiac.ser210.pawpedia.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import edu.quinnipiac.ser210.pawpedia.SQL.Dog
import edu.quinnipiac.ser210.pawpedia.SQL.DogDataAccess
import edu.quinnipiac.ser210.pawpedia.ui.theme.PawpediaTheme

@Composable
fun ListScreen() {
    PawpediaTheme {

    }
}

@Composable
fun dogCard(dog: Dog) {
    Row() {
        Image( //Dog PFP Img
            painterResource(dog.id),
            contentDescription = "Dog Pic"
        )
        Column() {
            Text( //Breed Name
                text = dog.breed_name
            )
        }
    }
}