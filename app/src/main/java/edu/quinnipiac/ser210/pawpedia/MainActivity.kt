package edu.quinnipiac.ser210.pawpedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.quinnipiac.ser210.pawpedia.Screens.ListScreen
import edu.quinnipiac.ser210.pawpedia.ui.theme.PawpediaTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.quinnipiac.ser210.pawpedia.Screens.DetailsScreen
import edu.quinnipiac.ser210.pawpedia.Screens.HomeScreen
//import edu.quinnipiac.ser210.pawpedia.Screens.ToggleTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            var isDark by remember { mutableStateOf(false) }


            PawpediaTheme(darkTheme = isDark) {
            val navController = rememberNavController()
            val viewModel: DogViewModel = viewModel()

                NavHost(navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            isDarkTheme = isDark,
                            onToggleTheme = { isDark = !isDark },
                            onSizeSelected = { size ->
                                navController.navigate("list/$size")
                            }
                        )
                    }
                    composable(
                        route = "list/{size}",
                        arguments = listOf(navArgument("size") { type = NavType.IntType })
                    ) { backStack ->
                        val size = backStack.arguments!!.getInt("size")
                        ListScreen(
                            size = size,
                            viewModel = viewModel,
                            isDarkTheme = isDark/*OogaBooga needs update (Pass through or hoist state(?)*/,
                            onToggleTheme = { isDark = !isDark},
                            onDogSelected = { dogId ->
                                navController.navigate("detail/$dogId")
                            }
                        )
                    }
                    composable(
                        route = "detail/{dogId}",
                        arguments = listOf(navArgument("dogId") { type = NavType.IntType})
                    ) { backStack ->
                        val dogId = backStack.arguments!!.getInt("dogId")
                        //load selected dog and display when ready
                        LaunchedEffect(dogId) { viewModel.loadDogById(dogId) }
                        viewModel.selectedDog.collectAsState().value?.let { dog ->
                            DetailsScreen(dog = dog, onToggleTheme = { isDark = !isDark })
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    val dogViewModel: DogViewModel = viewModel()
//    ListScreen(size = 0, viewModel = dogViewModel, isDarkTheme = true, onToggleTheme = {})
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    PawpediaTheme {
//        Greeting("Android")
//    }
//}

