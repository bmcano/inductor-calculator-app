package com.brandoncano.inductancecalculator.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.brandoncano.inductancecalculator.constants.Links
import com.brandoncano.inductancecalculator.navigation.calculators.colorToValueScreen
import com.brandoncano.inductancecalculator.navigation.calculators.smdScreen
import com.brandoncano.inductancecalculator.navigation.calculators.valueToColorScreen
import com.brandoncano.sharedcomponents.data.Apps
import com.brandoncano.sharedcomponents.navigation.SharedScreens
import com.brandoncano.sharedcomponents.navigation.donateScreen
import com.brandoncano.sharedcomponents.navigation.viewOurAppsScreen
import com.brandoncano.sharedcomponents.utils.OpenLink

/**
 * Note: Keep each navigation route in alphabetical order
 */

@Composable
fun Navigation(onOpenThemeDialog: () -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        aboutScreen(navController)
        colorToValueScreen(navController)
        homeScreen(navController, onOpenThemeDialog)
        smdScreen(navController)
        valueToColorScreen(navController)
        // from shared library
        donateScreen(navController)
        viewOurAppsScreen(navController, Apps.Inductor)
    }
}

fun navigateToAbout(navController: NavHostController) {
    navController.navigate(Screen.About.route)
}

fun navigateToColorToValue(navController: NavHostController) {
    navController.navigate(Screen.ColorToValue.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToValueToColor(navController: NavHostController) {
    navController.navigate(Screen.ValueToColor.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToSmd(navController: NavHostController) {
    navController.navigate(Screen.Smd.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToOurApps(navController: NavHostController) {
    navController.navigate(SharedScreens.ViewOurApps.route)
}

fun navigateToDonate(navController: NavHostController) {
    navController.navigate(SharedScreens.Donate.route)
}

fun navigateToGooglePlay(context: Context) {
    OpenLink.execute(context, Links.INDUCTOR_PLAYSTORE)
}
