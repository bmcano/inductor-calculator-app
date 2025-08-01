package com.brandoncano.inductancecalculator.navigation

/**
 * Note: Keep screens in alphabetical order
 */
sealed class Screen(val route: String) {
    data object About : Screen("about")
    data object ColorToValue: Screen("color_to_value")
    data object Donate : Screen("donate")
    data object Home : Screen("home")
    data object LearnColorCodes : Screen("learn_color_codes")
    data object LearnSmdCodes : Screen("learn_smd_codes")
    data object Smd: Screen("smd_screen")
    data object ValueToColor: Screen("value_to_color")
    data object ViewOurApps : Screen("view_our_apps")
}
