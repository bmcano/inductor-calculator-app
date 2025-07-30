package com.brandoncano.inductancecalculator.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.brandoncano.inductancecalculator.firebase.FirebaseRemoteConfigKeys
import com.brandoncano.inductancecalculator.firebase.getStringOrEmpty
import com.brandoncano.inductancecalculator.navigation.calculators.colorToValueScreen
import com.brandoncano.inductancecalculator.navigation.calculators.smdScreen
import com.brandoncano.inductancecalculator.navigation.calculators.valueToColorScreen
import com.brandoncano.inductancecalculator.navigation.learn.learnColorCodes
import com.brandoncano.inductancecalculator.navigation.learn.learnSmdCodes
import com.brandoncano.resistancecalculator.util.OpenLink

/**
 * Note: Keep each navigation route in alphabetical order
 *
 * TODO - Convert to Nav3 when it is ready and stable
 */

private const val TAG = "Navigation"

@Composable
fun Navigation(onOpenAppThemeDialog: () -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        aboutScreen(navController)
        colorToValueScreen(navController)
        donateScreen(navController)
        homeScreen(navController, onOpenAppThemeDialog)
        learnColorCodes(navController)
        learnSmdCodes(navController)
        smdScreen(navController)
        valueToColorScreen(navController)
        viewOurAppsScreen(navController)
    }
}

fun popBackStackSafely(navController: NavHostController) {
    if (navController.previousBackStackEntry != null) {
        navController.popBackStack()
    } else {
        Log.e(TAG, "Attempted navController.popBackStack(), but no BackStackEntry exists.")
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

fun navigateToLearnColorCodes(navController: NavHostController) {
    navController.navigate(Screen.LearnColorCodes.route)
}

fun navigateToLearnSmdCodes(navController: NavHostController) {
    navController.navigate(Screen.LearnSmdCodes.route)
}

fun navigateToOurApps(navController: NavHostController) {
    navController.navigate(Screen.ViewOurApps.route)
}

fun navigateToDonate(navController: NavHostController) {
    navController.navigate(Screen.Donate.route)
}

fun navigateToGooglePlay(context: Context) {
    val playstoreInductorLink = FirebaseRemoteConfigKeys.PLAYSTORE_INDUCTOR.getStringOrEmpty()
    OpenLink.execute(context, playstoreInductorLink)
}
