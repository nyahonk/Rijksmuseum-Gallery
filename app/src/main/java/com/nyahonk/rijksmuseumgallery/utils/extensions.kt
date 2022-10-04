package com.nyahonk.rijksmuseumgallery.utils

fun String.addFirstNavArgument(arg: String): String {
    return "$this?$arg={$arg}"
}

fun String.addNextNavArgument(arg: String): String {
    return "$this&$arg={$arg}"
}