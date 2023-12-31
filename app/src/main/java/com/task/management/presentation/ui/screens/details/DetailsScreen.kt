package com.task.management.presentation.ui.screens.details

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.task.management.data.local.Task
import com.task.management.presentation.ui.theme.Black
import com.task.management.presentation.ui.theme.Blue

@Destination
@Composable
fun DetailsScreen(
    destinationsNavigator: DestinationsNavigator,
    task: Task
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            Color.Transparent,
            darkIcons = true
        )
        systemUiController.setNavigationBarColor(
            Black,
            darkIcons = false
        )
    }

    Scaffold(
        containerColor = Blue,
        topBar = {
            TopBarComponent(destinationsNavigator, task)
        }
    ) { paddingValues ->
        DetailsContent(paddingValues)
    }
}

