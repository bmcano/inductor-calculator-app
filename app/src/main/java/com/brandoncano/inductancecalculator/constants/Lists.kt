package com.brandoncano.inductancecalculator.constants

import com.brandoncano.inductancecalculator.data.DropdownItem

/**
 * Job: Holds the list of items for each dropdown
 */
object Lists {

    val UNITS = listOf(Symbols.UH, Symbols.MH)

    val INDUCTOR_SIG_FIGS = listOf(
        DropdownItem(Colors.BLACK, "0"),
        DropdownItem(Colors.BROWN, "1"),
        DropdownItem(Colors.RED, "2"),
        DropdownItem(Colors.ORANGE, "3"),
        DropdownItem(Colors.YELLOW, "4"),
        DropdownItem(Colors.GREEN, "5"),
        DropdownItem(Colors.BLUE, "6"),
        DropdownItem(Colors.VIOLET, "7"),
        DropdownItem(Colors.GRAY, "8"),
        DropdownItem(Colors.WHITE, "9"),
    )

    val INDUCTOR_SIG_FIGS_NO_BLACK = INDUCTOR_SIG_FIGS.drop(1)

    val INDUCTOR_MULTIPLIERS = listOf(
        DropdownItem(Colors.BLACK, "x 1"),
        DropdownItem(Colors.BROWN, "x 10"),
        DropdownItem(Colors.RED, "x 100"),
        DropdownItem(Colors.ORANGE, "x 1000"),
        DropdownItem(Colors.YELLOW, "x 10000"),
        DropdownItem(Colors.GOLD, "x 0.1"),
        DropdownItem(Colors.SILVER, "x 0.01"),
    )

    val INDUCTOR_TOLERANCES = listOf(
        DropdownItem(Colors.BLACK, "${Symbols.PM}20%"),
        DropdownItem(Colors.BROWN, "${Symbols.PM}1%"),
        DropdownItem(Colors.RED, "${Symbols.PM}2%"),
        DropdownItem(Colors.ORANGE, "${Symbols.PM}3%"),
        DropdownItem(Colors.YELLOW, "${Symbols.PM}4%"),
        DropdownItem(Colors.GOLD, "${Symbols.PM}5%"),
        DropdownItem(Colors.SILVER, "${Symbols.PM}10%"),
    )
}