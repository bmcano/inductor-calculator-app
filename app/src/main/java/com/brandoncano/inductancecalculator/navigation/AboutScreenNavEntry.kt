package com.brandoncano.inductancecalculator.navigation

import android.content.Context
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.inductancecalculator.constants.Links
import com.brandoncano.inductancecalculator.ui.screens.about.AboutScreen
import com.brandoncano.sharedcomponents.utils.OpenLink

fun NavGraphBuilder.aboutScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.About.route,
        enterTransition = { slideInVertically(initialOffsetY = { it }) },
        exitTransition = { slideOutVertically(targetOffsetY = { it }) },
    ) {
        val context = LocalContext.current
        AboutScreen(
            onNavigateBack = { navHostController.popBackStack() },
            onViewPrivacyPolicyTapped = { navigateToPrivacyPolicy(context) },
            onRateThisAppTapped = { navigateToGooglePlay(context) },
            onViewOurAppsTapped = { navigateToOurApps(navHostController) },
            onDonateTapped = { navigateToDonate(navHostController) },
        )
    }
}

private fun navigateToPrivacyPolicy(context: Context) {
    OpenLink.execute(context, Links.PRIVACY_POLICY)
}
