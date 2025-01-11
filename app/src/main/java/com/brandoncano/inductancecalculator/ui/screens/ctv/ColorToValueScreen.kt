package com.brandoncano.inductancecalculator.ui.screens.ctv

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
import com.brandoncano.inductancecalculator.model.ctv.InductorCtv
import com.brandoncano.inductancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.inductancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.inductancecalculator.ui.theme.InductorCalculatorTheme
import com.brandoncano.inductancecalculator.util.Sdk
import com.brandoncano.inductancecalculator.util.shareableText
import com.brandoncano.sharedcomponents.composables.AppDivider
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.composables.ShareImageMenuItem
import com.brandoncano.sharedcomponents.composables.ShareTextMenuItem

@Composable
fun ColorToValueScreen(
    openMenu: MutableState<Boolean>,
    reset: MutableState<Boolean>,
    inductor: InductorCtv,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onUpdateBand: (Int, String) -> Unit,
    onLearnColorCodesTapped: () -> Unit,
) {
    val picture = remember { Picture() }
    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.title_color_to_value),
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
        ColorToValueScreenContent(
            paddingValues = paddingValues,
            picture = picture,
            reset = reset,
            inductor = inductor,
            onUpdateBand = onUpdateBand,
            onLearnColorCodesTapped = onLearnColorCodesTapped,
        )
    }
}

@Composable
private fun ColorToValueScreenContent(
    paddingValues: PaddingValues,
    picture: Picture,
    reset: MutableState<Boolean>,
    inductor: InductorCtv,
    onUpdateBand: (Int, String) -> Unit,
    onLearnColorCodesTapped: () -> Unit,
) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        InductorDisplay(picture, inductor)
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 24.dp),
            label = R.string.hint_band_1,
            selectedOption = inductor.band1,
            items = DropdownLists.NUMBER_LIST_NO_BLACK,
            reset = reset.value,
            onOptionSelected = { onUpdateBand(1, it) },
        )
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = R.string.hint_band_2,
            selectedOption = inductor.band2,
            items = DropdownLists.NUMBER_LIST,
            reset = reset.value,
            onOptionSelected = { onUpdateBand(2, it) },
        )
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = R.string.hint_band_3,
            selectedOption = inductor.band3,
            items = DropdownLists.MULTIPLIER_LIST,
            reset = reset.value,
            onOptionSelected = { onUpdateBand(3, it) },
        )
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = R.string.hint_band_4,
            selectedOption = inductor.band4,
            items = DropdownLists.TOLERANCE_LIST,
            reset = reset.value,
            onOptionSelected = { onUpdateBand(4, it) },
        )
        AppDivider(modifier = Modifier.padding(vertical = 24.dp))
        FiveBandInductorInfo(onLearnColorCodesTapped)
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@AppScreenPreviews
@Composable
private fun ColorToValueScreenPreview() {
    InductorCalculatorTheme {
        ColorToValueScreen(
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            inductor = InductorCtv(),
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onUpdateBand = { _, _ -> },
            onLearnColorCodesTapped = {},
        )
    }
}
