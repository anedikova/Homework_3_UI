package io.mmaltsev.vkeducation.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.mmaltsev.vkeducation.presentation.appdetails.AppDetailsScreen
import io.mmaltsev.vkeducation.presentation.appslist.AppsListRoute
import io.mmaltsev.vkeducation.presentation.theme.VkEducationTheme

private object Routes {
    const val LIST = "list"
    const val DETAILS = "details"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VkEducationTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.LIST,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    composable(Routes.LIST) {
                        AppsListRoute(
                            onAppClick = {
                                navController.navigate(Routes.DETAILS)
                            }
                        )
                    }

                    composable(Routes.DETAILS) {
                        AppDetailsScreen()
                    }
                }
            }
        }
    }
}