package com.alexzh.myexpensio.core

import java.text.NumberFormat
import java.util.*

class DefaultAmountCurrencyFormatter : AmountCurrencyFormatter {

    override fun format(amount: Double, currencyCode: String): String {
        val numberFormat = NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
            currency = Currency.getInstance(currencyCode)
        }
        return numberFormat.format(amount)
    }
}