package com.ahmbarish.flickerbrowser

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

internal const val FLICKER_QUERY = "FLICKER_QUERY"
internal const val PHOTO_TRANSFER = "PHOTO_TRANSFER"

//Send details from another acitivty to another
@SuppressLint("Registered")
open class BaseClass : AppCompatActivity() {
    private val TAG = "BaseActivity"

    internal fun activateToolbar(enableHome : Boolean) {
        Log.d(TAG,".activateToolbar")

        var toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }
}