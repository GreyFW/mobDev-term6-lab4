package com.example.bookbrowser.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookbrowser.ui.screens.BookDetailsScreen
import com.example.bookbrowser.ui.screens.BookListScreen
import com.example.bookbrowser.ui.screens.TesterScreen

object Screen {
    const val List = "book_list"
    const val Details = "book_details/{bookId}"
    const val Tester = "tester_screen"
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.List) {

        composable(Screen.List) {
            BookListScreen(
                onBookClick = { id ->
                    navController.navigate("book_details/$id")
                },
                onTesterClick = {
                    navController.navigate(Screen.Tester)
                }
            )
        }

        composable(
            route = Screen.Details,
            arguments = listOf(
                navArgument("bookId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            BookDetailsScreen(
                bookId = backStackEntry.arguments?.getString("bookId"),
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Tester) {
            TesterScreen(onBack = { navController.popBackStack() })
        }
    }
}