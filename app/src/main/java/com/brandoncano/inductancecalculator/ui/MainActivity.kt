package com.brandoncano.inductancecalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brandoncano.inductancecalculator.navigation.Navigation
import com.brandoncano.inductancecalculator.ui.theme.InductorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppThemeDialog
import com.brandoncano.sharedcomponents.data.ThemeMode
import com.brandoncano.sharedcomponents.model.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()
            val themeMode by viewModel.themeMode.observeAsState(ThemeMode.SYSTEM_DEFAULT)
            val showThemeDialog = viewModel.showThemeDialog.collectAsState()

            InductorCalculatorTheme(themeMode = themeMode) {
                if (showThemeDialog.value) {
                    AppThemeDialog(
                        currentThemeMode = themeMode,
                        onThemeSelected = { viewModel.setThemeMode(it) },
                        onDismissRequest = { viewModel.closeThemeDialog() }
                    )
                }

                Navigation(
                    onOpenThemeDialog = { viewModel.openThemeDialog() },
                )
            }
        }
    }
}
