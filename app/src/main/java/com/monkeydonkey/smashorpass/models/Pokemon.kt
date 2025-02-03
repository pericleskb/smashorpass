package com.monkeydonkey.smashorpass.models

import android.graphics.Bitmap

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int, //In decimeters. Divide by 10 to get meters
    val frontMain: Bitmap? = null,
    val frontSecondary: Bitmap? = null,
) {
}