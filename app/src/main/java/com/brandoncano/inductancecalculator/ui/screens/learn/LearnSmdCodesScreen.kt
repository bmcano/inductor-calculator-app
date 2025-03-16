package com.brandoncano.inductancecalculator.ui.screens.learn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.brandoncano.inductancecalculator.ui.theme.InductorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTopAppBar
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleLargeTitle
import com.brandoncano.sharedcomponents.text.textStyleTitle

@Composable
fun LearnSmdCodesScreen(
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
    ) { paddingValues ->
        LearnSmdCodesScreenContent(
            paddingValues
        )
    }
}

@Composable
private fun LearnSmdCodesScreenContent(paddingValues: PaddingValues) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = stringResource(R.string.learn_intro_title),
            modifier = Modifier.padding(vertical = 12.dp),
            style = textStyleLargeTitle(),
        )
        Text(
            text = stringResource(R.string.learn_smd_intro_body),
            modifier = Modifier.padding(bottom = 32.dp),
            style = textStyleBody().onSurfaceVariant(),
        )

        Text(
            text = stringResource(R.string.learn_smd_inductor_codes_title),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleLargeTitle(),
        )
        Text(
            text = stringResource(R.string.learn_smd_inductor_codes_body),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        Text(
            text = stringResource(R.string.learn_smd_three_code_body),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        CodeExampleCard(
            code = "Code: xyz",
            description = stringResource(R.string.learn_smd_three_digit_formula),
        )
        Text(
            text = stringResource(R.string.learn_smd_code_example),
            modifier = Modifier.padding(vertical = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        CodeExampleCard(
            code = "Code: 472",
            description = stringResource(R.string.learn_smd_three_digit_example),
        )
        Text(
            text = stringResource(R.string.learn_smd_tolerance_table),
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp),
            style = textStyleTitle(),
        )
        SmdToleranceTable()
        DisclaimerText()
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@AppScreenPreviews
@Composable
private fun LearnSmdCodesPreview() {
    InductorCalculatorTheme {
        LearnSmdCodesScreen {}
    }
}
