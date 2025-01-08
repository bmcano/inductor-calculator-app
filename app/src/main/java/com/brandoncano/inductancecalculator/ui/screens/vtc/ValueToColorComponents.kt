package com.brandoncano.inductancecalculator.ui.screens.vtc

import android.graphics.Picture
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.constants.Colors
import com.brandoncano.inductancecalculator.model.vtc.InductorVtc
import com.brandoncano.inductancecalculator.ui.screens.ctv.ImageColorPair
import com.brandoncano.inductancecalculator.ui.screens.ctv.InductanceText
import com.brandoncano.inductancecalculator.ui.screens.ctv.InductorRow
import com.brandoncano.inductancecalculator.util.Sdk
import com.brandoncano.sharedcomponents.composables.DrawContent

@Composable
fun InductorDisplay(picture: Picture, inductor: InductorVtc, isError: Boolean) {
    if (Sdk.isAtLeastAndroid7()) {
        DrawContent(picture) {
            InductorLayout(inductor, isError)
        }
    } else {
        InductorLayout(inductor, isError)
    }
}

@Composable
fun InductorLayout(inductor: InductorVtc, isError: Boolean) {
    Column(
        modifier = Modifier.padding(top = 16.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        InductorRow(
            ImageColorPair(R.drawable.img_inductor_wire, Colors.INDUCTOR_WIRE),
            ImageColorPair(R.drawable.img_inductor_end_left, Colors.INDUCTOR_GREEN),
            ImageColorPair(R.drawable.img_inductor_band_96, inductor.band1),
            ImageColorPair(R.drawable.img_inductor_curve_left, Colors.INDUCTOR_GREEN),
            ImageColorPair(R.drawable.img_inductor_band_64, inductor.band2),
            ImageColorPair(R.drawable.img_inductor_band_64_wide, Colors.INDUCTOR_GREEN),
            ImageColorPair(R.drawable.img_inductor_band_64, inductor.band3),
            ImageColorPair(R.drawable.img_inductor_curve_right, Colors.INDUCTOR_GREEN),
            ImageColorPair(R.drawable.img_inductor_band_96, inductor.tolerance),
            ImageColorPair(R.drawable.img_inductor_end_right, Colors.INDUCTOR_GREEN),
            ImageColorPair(R.drawable.img_inductor_wire, Colors.INDUCTOR_WIRE),
        )
        val text = when {
            inductor.isEmpty() -> stringResource(id = R.string.default_vtc_value)
            isError -> stringResource(id = R.string.error_na)
            else -> inductor.getInductanceValue()
        }
        InductanceText(text)
    }
}
