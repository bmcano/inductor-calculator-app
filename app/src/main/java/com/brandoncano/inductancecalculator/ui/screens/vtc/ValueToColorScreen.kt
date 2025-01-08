package com.brandoncano.inductancecalculator.ui.screens.vtc

import android.graphics.Picture
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.constants.Links
import com.brandoncano.inductancecalculator.data.DropdownLists
import com.brandoncano.inductancecalculator.model.vtc.InductorVtc
import com.brandoncano.inductancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.inductancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.inductancecalculator.ui.screens.ctv.FiveBandInductorInfo
import com.brandoncano.inductancecalculator.util.Sdk
import com.brandoncano.inductancecalculator.util.shareableText
import com.brandoncano.sharedcomponents.composables.AppDivider
import com.brandoncano.sharedcomponents.composables.AppDropDownMenu
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTextField
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.composables.ShareImageMenuItem
import com.brandoncano.sharedcomponents.composables.ShareTextMenuItem

@Composable
fun ValueToColorScreen(
    inductor: InductorVtc,
    isError: Boolean,
    openMenu: MutableState<Boolean>,
    reset: MutableState<Boolean>,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String, String, String, Boolean) -> Unit,
) {
    val picture = remember { Picture() }
    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.title_value_to_color),
                interactionSource = remember { MutableInteractionSource() },
                showMenu = openMenu,
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
            ) {
                ClearSelectionsMenuItem(onClearSelectionsTapped)
                ShareTextMenuItem(
                    text = inductor.shareableText(),
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
    ) { paddingValues ->
        ValueToColorScreenContent(
            paddingValues = paddingValues,
            picture = picture,
            inductor = inductor,
            isError = isError,
            reset = reset,
            onValueChanged = onValueChanged,
        )
    }
}

@Composable
private fun ValueToColorScreenContent(
    paddingValues: PaddingValues,
    picture: Picture,
    inductor: InductorVtc,
    isError: Boolean,
    reset: MutableState<Boolean>,
    onValueChanged: (String, String, String, Boolean) -> Unit,
) {
    val inductance = remember { mutableStateOf(inductor.inductance) }
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InductorDisplay(picture, inductor, isError)
        AppTextField(
            label = stringResource(id = R.string.hint_inductance),
            modifier = Modifier.padding(top = 32.dp),
            value = inductance,
            reset = reset.value,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_inductance)
        ) {
            inductance.value = it
            onValueChanged(inductance.value, inductor.units, inductor.tolerance, false)
        }
        AppDropDownMenu(
            label = stringResource(id = R.string.hint_units),
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = inductor.units,
            items = DropdownLists.UNITS_LIST,
            reset = reset.value,
            onOptionSelected = {
                onValueChanged(inductance.value, it, inductor.tolerance, true)
            }
        )
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = R.string.hint_band_4,
            selectedOption = inductor.tolerance,
            items = DropdownLists.TOLERANCE_LIST,
            reset = reset.value,
            isValueToColor = true,
            onOptionSelected = {
                onValueChanged(inductance.value, inductor.units, it, true)
            }
        )
        AppDivider(modifier = Modifier.padding(vertical = 24.dp))
        FiveBandInductorInfo()
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@AppScreenPreviews
@Composable
private fun ValueToColorScreenPreview() {
    ValueToColorScreen(
        inductor = InductorVtc(),
        isError = false,
        openMenu = remember { mutableStateOf(false) },
        reset = remember { mutableStateOf(false) },
        onNavigateBack = {},
        onClearSelectionsTapped = {},
        onAboutTapped = {},
        onValueChanged = { _, _, _, _ -> },
    )
}
