package com.brandoncano.inductancecalculator.navigation.learn

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.inductancecalculator.navigation.Screen
import com.brandoncano.inductancecalculator.ui.screens.info.LearnSmdCodesScreen

fun NavGraphBuilder.learnSmdCodes(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.LearnSmdCodes.route,
        enterTransition = { slideInVertically(initialOffsetY = { it }) },
        exitTransition = { slideOutVertically(targetOffsetY = { it }) },
    ) {
        LearnSmdCodesScreen(
            onNavigateBack = { navHostController.popBackStack() },
        )
    }
}