package com.brandoncano.inductancecalculator.util

import com.brandoncano.inductancecalculator.data.SmdTolerance
import com.brandoncano.inductancecalculator.to.InductorCtv
import com.brandoncano.inductancecalculator.model.smd.InductorSmd
import com.brandoncano.inductancecalculator.model.vtc.InductorVtc

fun InductorCtv.formatInductance(): String {
    return InductanceFormatter.execute(this)
}

fun InductorCtv.shareableText(): String {
    return "$this\n${this.formatInductance()}"
}

fun InductorVtc.isInvalidInput(): Boolean {
    return !IsValidInductance.execute(this.inductance, this.units)
}

fun InductorVtc.formatInductor() {
    InductorFormatter.execute(this)
}

fun InductorVtc.shareableText(): String {
    return "$this\n${this.getInductanceValue()}"
}

fun String.adjustValueForSharing(): String {
    val color = ColorFinder.textToColor(this)
    return ColorFinder.colorToColorText(color)
}

fun InductorSmd.isSmdInputInvalid(): Boolean {
    return !IsValidSmdCode.execute(this.code)
}

fun InductorSmd.formatInductance(): String {
    return InductanceSmdFormatter.execute(this)
}

fun InductorSmd.formatTolerance(): String {
    return SmdTolerance.getTolerance(this.tolerance)
}
