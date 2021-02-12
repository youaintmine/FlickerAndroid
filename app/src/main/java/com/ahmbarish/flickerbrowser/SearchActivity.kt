package com.ahmbarish.flickerbrowser

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : BaseClass() {
    private val TAG = "SearchActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, ".onCreate: starts")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activateToolbar(true)
        Log.d(TAG,".onCreate: ends")
    }
}