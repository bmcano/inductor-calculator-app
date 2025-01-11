package com.brandoncano.inductancecalculator.ui.screens.ctv

import android.graphics.Picture
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.constants.Colors
import com.brandoncano.inductancecalculator.model.ctv.InductorCtv
import com.brandoncano.inductancecalculator.ui.theme.InductorCalculatorTheme
import com.brandoncano.inductancecalculator.util.ColorFinder
import com.brandoncano.inductancecalculator.util.Sdk
import com.brandoncano.inductancecalculator.util.formatInductance
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppCard
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.composables.DrawContent
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleTitle

data class ImageColorPair(@DrawableRes val drawableRes: Int, val color: String)

@Composable
fun InductorDisplay(picture: Picture, inductor: InductorCtv) {
    if (Sdk.isAtLeastAndroid7()) {
        DrawContent(picture) {
            InductorLayout(inductor)
        }
    } else {
        InductorLayout(inductor)
    }
}

@Composable
fun InductorLayout(inductor: InductorCtv) {
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
            ImageColorPair(R.drawable.img_inductor_band_96, inductor.band4),
            ImageColorPair(R.drawable.img_inductor_end_right, Colors.INDUCTOR_GREEN),
            ImageColorPair(R.drawable.img_inductor_wire, Colors.INDUCTOR_WIRE),
        )
        val text = if (inductor.isEmpty()) {
            stringResource(id = R.string.default_ctv_value)
        } else {
            inductor.formatInductance()
        }
        InductanceText(text)
    }
}

@Composable
fun InductorRow(vararg inductorImages: ImageColorPair) {
    Row(horizontalArrangement = Arrangement.Absolute.Center) {
        inductorImages.forEach { inductorImage ->
            val color = ColorFinder.textToColor(inductorImage.color)
            InductorImage(inductorImage.drawableRes, color)
        }
    }
}

@Composable
fun InductorImage(@DrawableRes drawableRes: Int, color: Color) {
    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color),
    )
}

@Composable
fun InductanceText(inductance: String) {
    AppCard(modifier = Modifier.padding(top = 12.dp)) {
        Text(
            text = inductance,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp),
            style = textStyleTitle(),
        )
    }
}

@Composable
fun FiveBandInductorInfo(onLearnColorCodesTapped: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.learn_color_codes_headline),
            modifier = Modifier.padding(bottom = 16.dp),
            style = textStyleHeadline(),
        )
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Lightbulb,
                text = stringResource(id = R.string.learn_color_codes_headline),
                onClick = onLearnColorCodesTapped,
            ),
        )
    }
}

@AppComponentPreviews
@Composable
private fun InductorLayoutsPreview() {
    InductorCalculatorTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InductorLayout(InductorCtv())
        }
    }
}
