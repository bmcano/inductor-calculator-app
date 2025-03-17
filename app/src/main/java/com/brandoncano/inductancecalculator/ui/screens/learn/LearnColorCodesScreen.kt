package com.brandoncano.inductancecalculator.ui.screens.learn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.constants.Colors
import com.brandoncano.inductancecalculator.model.ctv.InductorCtv
import com.brandoncano.inductancecalculator.ui.screens.ctv.InductorLayout
import com.brandoncano.inductancecalculator.ui.theme.InductorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTopAppBar
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleTitle

@Composable
fun LearnColorCodesScreen(
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopAppBar(
                titleText = stringResource(R.string.learn_title),
                navigationIcon =  Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        LearnColorCodesScreenContent(paddingValues)
    }
}

@Composable
private fun LearnColorCodesScreenContent(paddingValues: PaddingValues) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
    ) {
        Text(
            text = stringResource(R.string.learn_intro_title),
            modifier = Modifier.padding(vertical = 12.dp),
            style = textStyleTitle(),
        )
        Text(
            text = stringResource(R.string.learn_color_intro_body),
            modifier = Modifier.padding(bottom = 32.dp),
            style = textStyleBody().onSurfaceVariant(),
        )

        Text(
            text = stringResource(R.string.learn_color_meaning_title),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleTitle(),
        )
        Text(
            text = stringResource(R.string.learn_color_meaning_body),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        InductorColorCodeTable()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.learn_color_four_band_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleTitle(),
        )
        Text(
            text = stringResource(R.string.learn_color_four_band_body),
            style = textStyleBody().onSurfaceVariant(),
            modifier = Modifier.padding(bottom = 12.dp),
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            InductorLayout(InductorCtv(Colors.YELLOW, Colors.VIOLET, Colors.RED, Colors.GOLD))
        }
        Text(
            text = stringResource(R.string.learn_color_code_explanation),
            modifier = Modifier.padding(vertical = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        CodeExampleCard(
            code = stringResource(R.string.learn_color_four_band_code),
            description = stringResource(R.string.learn_color_four_band_description),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.learn_color_five_band_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleTitle(),
        )
        Text(
            text = stringResource(R.string.learn_color_five_band_body),
            style = textStyleBody().onSurfaceVariant(),
        )
        DisclaimerText()
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@AppScreenPreviews
@Composable
private fun LearnColorCodesPreview() {
    InductorCalculatorTheme {
        LearnColorCodesScreen {}
    }
}
