package edu.quinnipiac.ser210.pawpedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.quinnipiac.ser210.pawpedia.Screens.ListScreen
import edu.quinnipiac.ser210.pawpedia.ui.theme.PawpediaTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.quinnipiac.ser210.pawpedia.Screens.HomeScreen
import edu.quinnipiac.ser210.pawpedia.Screens.ToggleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToggleTheme()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val dogViewModel: DogViewModel = viewModel()
    ListScreen(size = 0, viewModel = dogViewModel, isDarkTheme = true, onToggleTheme = {})
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PawpediaTheme {
        Greeting("Android")
    }
}

