package com.brandoncano.inductancecalculator.navigation.calculators

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.inductancecalculator.model.InductorViewModelFactory
import com.brandoncano.inductancecalculator.model.ctv.InductorCtvViewModel
import com.brandoncano.inductancecalculator.navigation.Screen
import com.brandoncano.inductancecalculator.navigation.navigateToAbout
import com.brandoncano.inductancecalculator.navigation.navigateToLearnColorCodes
import com.brandoncano.inductancecalculator.ui.screens.ctv.ColorToValueScreen

fun NavGraphBuilder.colorToValueScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.ColorToValue.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val openMenu = remember { mutableStateOf(false) }
        val reset = remember { mutableStateOf(false) }
        val viewModel: InductorCtvViewModel = viewModel(factory = InductorViewModelFactory(context))
        val inductor by viewModel.inductor.collectAsState()

        ColorToValueScreen(
            openMenu = openMenu,
            reset = reset,
            inductor = inductor,
            onNavigateBack = { navHostController.popBackStack() },
            onClearSelectionsTapped = {
                openMenu.value = false
                reset.value = true
                viewModel.clear()
                focusManager.clearFocus()
            },
            onAboutTapped = {
                openMenu.value = false
                navigateToAbout(navHostController)
            },
            onUpdateBand = { bandNumber, color ->
                reset.value = false
                viewModel.updateBand(bandNumber, color)
            },
            onLearnColorCodesTapped = { navigateToLearnColorCodes(navHostController) },
        )
    }
}
