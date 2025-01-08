package com.brandoncano.inductancecalculator.data

import com.brandoncano.inductancecalculator.constants.Colors
import com.brandoncano.inductancecalculator.constants.Symbols


/**
 * Job: Holds the list of items for each dropdown
 */
object DropdownLists {

    val NUMBER_LIST = listOf(
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

    val NUMBER_LIST_NO_BLACK = NUMBER_LIST.drop(1)

    val MULTIPLIER_LIST = listOf(
        DropdownItem(Colors.BLACK, "x 1"),
        DropdownItem(Colors.BROWN, "x 10"),
        DropdownItem(Colors.RED, "x 100"),
        DropdownItem(Colors.ORANGE, "x 1000"),
        DropdownItem(Colors.YELLOW, "x 10000"),
        DropdownItem(Colors.GOLD, "x 0.1"),
        DropdownItem(Colors.SILVER, "x 0.01"),
    )

    val TOLERANCE_LIST = listOf(
        DropdownItem(Colors.BLACK, "${Symbols.PM}20%"),
        DropdownItem(Colors.BROWN, "${Symbols.PM}1%"),
        DropdownItem(Colors.RED, "${Symbols.PM}2%"),
        DropdownItem(Colors.ORANGE, "${Symbols.PM}3%"),
        DropdownItem(Colors.YELLOW, "${Symbols.PM}4%"),
        DropdownItem(Colors.GOLD, "${Symbols.PM}5%"),
        DropdownItem(Colors.SILVER, "${Symbols.PM}10%"),
    )

    val UNITS_LIST = listOf(Symbols.UH, Symbols.MH)
}
