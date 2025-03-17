package com.brandoncano.inductancecalculator.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.constants.Symbols
import com.brandoncano.inductancecalculator.data.SmdTolerance
import com.brandoncano.inductancecalculator.ui.theme.black
import com.brandoncano.inductancecalculator.ui.theme.blue
import com.brandoncano.inductancecalculator.ui.theme.red
import com.brandoncano.inductancecalculator.ui.theme.violet
import com.brandoncano.inductancecalculator.ui.theme.white
import com.brandoncano.inductancecalculator.util.ColorFinder
import com.brandoncano.sharedcomponents.composables.AppCard
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.composables.AppDivider
import com.brandoncano.sharedcomponents.composables.AppStandardDivider
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleCallout
import com.brandoncano.sharedcomponents.text.textStyleCaption
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleSubhead

@Composable
fun DisclaimerText() {
    AppDivider(modifier = Modifier.padding(vertical = 24.dp))
    Text(
        text = stringResource(R.string.learn_disclaimer_footer),
        style = textStyleCaption().onSurfaceVariant(),
    )
}

@Composable
fun CodeExampleCard(code: String, description: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)) {
            Text(
                text = code,
                style = textStyleHeadline(),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = textStyleBody().onSurfaceVariant(),
            )
        }
    }
}

@Composable
fun EquationCard(equation: String) {
    AppCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = equation,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = textStyleCallout(),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun InductorColorCodeTable() {
    data class InductorColorCode(
        val color: String,
        val significantFigures: String,
        val multiplier: String,
        val tolerance: String,
    )

    val inductorColorCodes = listOf(
        InductorColorCode("Black", "0", "${Symbols.X}1", "${Symbols.PM}20%"),
        InductorColorCode("Brown", "1", "${Symbols.X}10", "${Symbols.PM}1%"),
        InductorColorCode("Red", "2", "${Symbols.X}100", "${Symbols.PM}2%"),
        InductorColorCode("Orange", "3", "${Symbols.X}1k", "${Symbols.PM}3%"),
        InductorColorCode("Yellow", "4", "${Symbols.X}10k", "${Symbols.PM}4%"),
        InductorColorCode("Green", "5", "-", "-"),
        InductorColorCode("Blue", "6", "-", "-"),
        InductorColorCode("Violet", "7", "-", "-"),
        InductorColorCode("Gray", "8", "-", "-"),
        InductorColorCode("White", "9", "-", "-"),
        InductorColorCode("Gold", "-", "${Symbols.X}0.1", "${Symbols.PM}5%"),
        InductorColorCode("Silver", "-", "${Symbols.X}0.01", "${Symbols.PM}10%"),
    )

    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val modifier =  Modifier.weight(1f)
            HeaderCell(modifier, "Color")
            HeaderCell(modifier, "Digit")
            HeaderCell(modifier, "Multiplier")
            HeaderCell(modifier, "Tolerance")
        }
        AppStandardDivider()
        inductorColorCodes.forEachIndexed { index, inductorColorCode ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val color = ColorFinder.textToColor(inductorColorCode.color)
                TableColorCell(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp)
                        .background(
                            color = color,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(vertical = 4.dp),
                    text = inductorColorCode.color,
                    backgroundColor = color,
                )
                val modifier = Modifier.padding(12.dp).weight(1f)
                TableCell(modifier, inductorColorCode.significantFigures)
                TableCell(modifier, inductorColorCode.multiplier)
                TableCell(modifier, inductorColorCode.tolerance)
            }
            if (index != inductorColorCodes.size - 1) AppStandardDivider()
        }
    }
}

@Composable
private fun HeaderCell(modifier: Modifier, text: String) {
    Text(
        text = text,
        modifier = modifier,
        style = textStyleSubhead(),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun TableCell(modifier: Modifier, text: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = textStyleCaption().onSurfaceVariant(),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun TableColorCell(modifier: Modifier, text: String, backgroundColor: Color) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val darkColors = setOf(black, red, blue, violet)
        val style = when (backgroundColor) {
            in darkColors -> textStyleCaption().white()
            else -> textStyleCaption().black()
        }
        Text(
            text = text,
            style = style,
            textAlign = TextAlign.Center,
        )
    }
}

@AppComponentPreviews
@Composable
fun SmdToleranceTable() {
    val tableData = SmdTolerance.getTolerancePairs()
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp)
    ) {
        Row {
            TableCell(text = stringResource(R.string.smd_info_tolerance_symbol), weight = column1Weight, style = textStyleCallout())
            TableCell(text =  stringResource(R.string.smd_info_tolerance_value), weight = column2Weight, style = textStyleCallout())
        }
        AppDivider(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp))
        tableData.forEach { pair ->
            val (id, text) = pair
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = id, weight = column1Weight, style = textStyleSubhead().onSurfaceVariant())
                TableCell(text = text, weight = column2Weight, style = textStyleSubhead().onSurfaceVariant())
            }
            if (tableData[tableData.size - 1] != pair) {
                AppDivider(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp))
            }
        }
    }
}

@Composable
private fun RowScope.TableCell(text: String, weight: Float, style: TextStyle) {
    Text(
        text = text,
        modifier = Modifier
            .weight(weight)
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        style = style,
        textAlign = TextAlign.Center,
    )
}
