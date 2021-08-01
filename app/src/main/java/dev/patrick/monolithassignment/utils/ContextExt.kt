package dev.patrick.monolithassignment.utils

import android.content.Context

fun Context.readFileFromAssets(filepath: String): String {
    return resources.assets.open(filepath).bufferedReader().use {
        it.readText()
    }
}