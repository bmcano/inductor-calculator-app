package com.brandoncano.inductancecalculator.ui.screens.smd

import android.graphics.Picture
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.constants.Links
import com.brandoncano.inductancecalculator.data.SmdTolerance
import com.brandoncano.inductancecalculator.model.smd.InductorSmd
import com.brandoncano.inductancecalculator.ui.theme.InductorCalculatorTheme
import com.brandoncano.inductancecalculator.util.Sdk
import com.brandoncano.sharedcomponents.composables.AboutAppMenuItem
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppDivider
import com.brandoncano.sharedcomponents.composables.AppDropDownMenu
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTextField
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.composables.ShareImageMenuItem
import com.brandoncano.sharedcomponents.composables.ShareTextMenuItem
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import java.util.Locale

@Composable
fun SmdScreen(
    openMenu: MutableState<Boolean>,
    reset: MutableState<Boolean>,
    inductor: InductorSmd,
    isError: Boolean,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String, String, Boolean) -> Unit,
    onLearnSmdCodesTapped: () -> Unit,
) {
    val picture = remember { Picture() }
    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.title_smd),
                interactionSource = remember { MutableInteractionSource() },
                showMenu = openMenu,
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
            ) {
                ClearSelectionsMenuItem(onClearSelectionsTapped)
                ShareTextMenuItem(
                    text = inductor.toString(),
                    showMenu = openMenu,
                )
                if (Sdk.isAtLeastAndroid7()) {
                    ShareImageMenuItem(
                        applicationId = Links.APPLICATION_ID,
                        showMenu = openMenu,
                        picture = picture,
                    )
                }
                FeedbackMenuItem(
                    app = Links.APP_NAME,
                    showMenu = openMenu,
                )
                AboutAppMenuItem(onAboutTapped)
            }
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        SmdScreenContent(
            paddingValues = paddingValues,
            picture = picture,
            reset = reset,
            inductor = inductor,
            isError = isError,
            onValueChanged = onValueChanged,
            onLearnSmdCodesTapped = onLearnSmdCodesTapped,
        )
    }
}

@Composable
private fun SmdScreenContent(
    paddingValues: PaddingValues,
    picture: Picture,
    reset: MutableState<Boolean>,
    inductor: InductorSmd,
    isError: Boolean,
    onValueChanged: (String, String, Boolean) -> Unit,
    onLearnSmdCodesTapped: () -> Unit,
) {
    val code = remember { mutableStateOf(inductor.code) }
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SmdInductorDisplay(picture, inductor, isError)
        AppTextField(
            label = stringResource(id = R.string.hint_smd_code),
            modifier = Modifier.padding(top = 32.dp),
            value = code,
            reset = reset.value,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_code),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        ) {
            code.value = it.uppercase(Locale.getDefault())
            onValueChanged(code.value, inductor.tolerance, false)
        }
        AppDropDownMenu(
            label = stringResource(id = R.string.hint_band_4),
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = inductor.tolerance,
            items = SmdTolerance.getLetterList(),
            reset = reset.value,
            onOptionSelected = { onValueChanged(code.value, it, true) }
        )
        AppDivider(modifier = Modifier.padding(vertical = 24.dp))
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = stringResource(R.string.learn_smd_codes_headline),
                modifier = Modifier.padding(bottom = 16.dp),
                style = textStyleHeadline(),
            )
            AppArrowCardButton(
                ArrowCardButtonContents(
                    imageVector = Icons.Outlined.Lightbulb,
                    text = stringResource(R.string.learn_color_codes_button),
                    onClick = onLearnSmdCodesTapped,
                )
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@AppScreenPreviews
@Composable
private fun SmdScreenPreview() {
    InductorCalculatorTheme {
        SmdScreen(
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            inductor = InductorSmd(),
            isError = false,
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onValueChanged = { _, _, _-> },
            onLearnSmdCodesTapped = {},
        )
    }
}
