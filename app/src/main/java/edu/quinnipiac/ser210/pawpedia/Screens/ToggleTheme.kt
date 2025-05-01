package edu.quinnipiac.ser210.pawpedia.Screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import edu.quinnipiac.ser210.pawpedia.ui.theme.PawpediaTheme

//@Composable
//fun ToggleTheme() {
//    var isDarkTheme by remember { mutableStateOf(false) }
//
//    PawpediaTheme(darkTheme = isDarkTheme) {
//        HomeScreen(
//            isDarkTheme = isDarkTheme,
//            onToggleTheme = { isDarkTheme = !isDarkTheme }
//        )
//    }
//}