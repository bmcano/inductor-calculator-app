package com.brandoncano.inductancecalculator.navigation.learn

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.inductancecalculator.navigation.Screen
import com.brandoncano.inductancecalculator.navigation.popBackStackSafely
import com.brandoncano.inductancecalculator.ui.screens.info.LearnColorCodesScreen

fun NavGraphBuilder.learnColorCodes(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.LearnColorCodes.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        LearnColorCodesScreen(
            onNavigateBack = { popBackStackSafely(navHostController) },
        )
    }
}
