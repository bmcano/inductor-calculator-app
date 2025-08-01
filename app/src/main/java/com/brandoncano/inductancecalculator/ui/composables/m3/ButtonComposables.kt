package com.brandoncano.inductancecalculator.ui.composables.m3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
private fun ButtonContent(
    icon: ImageVector?,
    iconContentDescription: String?,
    buttonLabel: String,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = iconContentDescription,
                modifier = Modifier.width(18.dp).align(Alignment.CenterVertically),
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = buttonLabel,
            style = MaterialTheme.typography.labelLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

/**
 * Elevated buttons provide separation from a visually prominent background.
 * Buttons at higher elevations typically have more emphasis in a design, and should be used sparingly.
 */
@Composable
fun M3ElevatedButton(
    buttonLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    useSquareShape: Boolean = false,
    minHeight: Dp = 56.dp,
    icon: ImageVector? = null,
    iconContentDescription: String? = null
) {
    val shape = if (useSquareShape) MaterialTheme.shapes.medium else ButtonDefaults.elevatedShape
    ElevatedButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = minHeight),
        enabled = enabled,
        shape = shape,
    ) {
        ButtonContent(icon, iconContentDescription, buttonLabel)
    }
}

/**
 * The filled button style has the most visual impact after the FAB, and should be used for important,
 * final actions that complete a flow, like Save, Join now, or Confirm.
 * Filled buttons have high visual impact when used for important actions.
 * Since they have such strong emphasis, the filled style should be used sparingly, ideally for only one action on a page.
 */
@Composable
fun M3FilledButton(
    buttonLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    useSquareShape: Boolean = false,
    minHeight: Dp = 56.dp,
    icon: ImageVector? = null,
    iconContentDescription: String? = null
) {
    val shape = if (useSquareShape) MaterialTheme.shapes.medium else ButtonDefaults.shape
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = minHeight),
        enabled = enabled,
        shape = shape,
    ) {
        ButtonContent(icon, iconContentDescription, buttonLabel)
    }
}

/**
 * The tonal button style is useful in contexts where a lower-priority button requires slightly more
 * emphasis than an outline would give, such as Next in an onboarding flow.
 * The tonal style has less emphasis than filled or elevated.
 */
@Composable
fun M3FilledTonalButton(
    buttonLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    useSquareShape: Boolean = false,
    minHeight: Dp = 56.dp,
    icon: ImageVector? = null,
    iconContentDescription: String? = null
) {
    val shape = if (useSquareShape) MaterialTheme.shapes.medium else ButtonDefaults.filledTonalShape
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = minHeight),
        enabled = enabled,
        shape = shape,
    ) {
        ButtonContent(icon, iconContentDescription, buttonLabel)
    }
}

/**
 * The outlined style is ideal for medium-emphasis buttons which contain actions that are important,
 * but aren't the primary action in a product.
 * Outlined buttons pair well with filled buttons to indicate alternative, secondary actions.
 * They should be placed on simple backgrounds, not visually prominent backgrounds such as images or videos.
 */
@Composable
fun M3OutlinedButton(
    buttonLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    useSquareShape: Boolean = false,
    minHeight: Dp = 56.dp,
    icon: ImageVector? = null,
    iconContentDescription: String? = null
) {
    val shape = if (useSquareShape) MaterialTheme.shapes.medium else ButtonDefaults.outlinedShape
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = minHeight),
        enabled = enabled,
        shape = shape,
        border = ButtonDefaults.outlinedButtonBorder(enabled),
    ) {
        ButtonContent(icon, iconContentDescription, buttonLabel)
    }
}

/**
 * The text button style should be used for the lowest priority actions, especially when presenting multiple options.
 * They should be placed on simple backgrounds, not visually prominent backgrounds such as images or videos.
 * Text buttons are often placed within components such as cards, dialogs, and snackbars.
 */
@Composable
fun M3TextButton(
    buttonLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    minHeight: Dp = 56.dp,
    icon: ImageVector? = null,
    iconContentDescription: String? = null
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = minHeight),
        enabled = enabled,
    ) {
        ButtonContent(icon, iconContentDescription, buttonLabel)
    }
}

@ComponentPreviews
@Composable
private fun ButtonsPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            M3ElevatedButton(
                buttonLabel = "Elevated",
                onClick = {},
                icon = Icons.Default.Favorite,
                iconContentDescription = "Favorite"
            )
            M3ElevatedButton(
                buttonLabel = "Elevated Square",
                onClick = {},
                useSquareShape = true,
                icon = Icons.Default.Add,
                iconContentDescription = "Add"
            )
            M3FilledButton(
                buttonLabel = "Filled",
                onClick = {},
                icon = Icons.Default.Favorite,
                iconContentDescription = "Favorite"
            )
            M3FilledButton(
                buttonLabel = "Filled Square",
                onClick = {},
                useSquareShape = true,
                icon = Icons.Default.Add,
                iconContentDescription = "Add"
            )
            M3FilledTonalButton(
                buttonLabel = "Tonal",
                onClick = {},
                icon = Icons.Default.Favorite,
                iconContentDescription = "Favorite"
            )
            M3FilledTonalButton(
                buttonLabel = "Tonal Square",
                onClick = {},
                useSquareShape = true,
                icon = Icons.Default.Add,
                iconContentDescription = "Add"
            )
            M3OutlinedButton(
                buttonLabel = "Outlined",
                onClick = {},
                icon = Icons.Default.Favorite,
                iconContentDescription = "Favorite"
            )
            M3OutlinedButton(
                buttonLabel = "Outlined Square",
                onClick = {},
                useSquareShape = true,
                icon = Icons.Default.Add,
                iconContentDescription = "Add"
            )
            M3TextButton(
                buttonLabel = "Text Button",
                onClick = {},
                icon = Icons.Default.Favorite,
                iconContentDescription = "Favorite"
            )
        }
    }
}
