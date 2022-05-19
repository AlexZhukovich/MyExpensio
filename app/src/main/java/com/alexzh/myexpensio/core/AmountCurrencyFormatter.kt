package com.alexzh.myexpensio.core

interface AmountCurrencyFormatter {

    fun format(amount: Double, currencyCode: String): String
}