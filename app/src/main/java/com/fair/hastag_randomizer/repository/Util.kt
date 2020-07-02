package com.fair.hastag_randomizer.repository

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context?.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
fun View.snack(message: String,duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, duration).setAction("Save"){

    }.show()
}
