package com.brandoncano.inductancecalculator.model.smd

import com.brandoncano.inductancecalculator.util.formatInductance
import com.brandoncano.inductancecalculator.util.formatTolerance

data class InductorSmd(
    var code: String = "",
    var tolerance: String = "",
) {
    fun isEmpty(): Boolean {
        return code.length < 3
    }

    override fun toString(): String {
        val inductance = this.formatInductance()
        return "$code $tolerance\n$inductance ${this.formatTolerance()}"
    }
}
