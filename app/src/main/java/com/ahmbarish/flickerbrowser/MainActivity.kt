package com.ahmbarish.flickerbrowser


import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), GetRawData.OnDownloadComplete, GetFlickerJsonData.OnDataAvailable, RecyclerItemClickListen.OnRecyclerClickListener {
    private val TAG = "MainActivity"

    private val flickerRecyclerViewAdapter = FlickerRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addOnItemTouchListener(RecyclerItemClickListen(this, recycler_view, this))
        recycler_view.adapter = flickerRecyclerViewAdapter

        val url = createUri("https://www.flickr.com/services/feeds/photos_public.gne","android,oreo","en-us", true)
        val getRawData = GetRawData(this)
//      getRawData.setDownloadCompleteListener(this)
        getRawData.execute(url)

        Log.d(TAG, "onCreate ends")
    }

    override fun onItemClick(view: View?, pos: Int) {
        Log.d(TAG, ".onItemClick starts")
        Toast.makeText(this,"Normal tap at $pos", Toast.LENGTH_SHORT).show()
    }

    override fun onItemHold(view: View, pos: Int) {
        Log.d(TAG, ".onItemHold starts")
        Toast.makeText(this, "Long Hold Tap at $pos", Toast.LENGTH_SHORT).show()
    }

    fun createUri(baseUrl : String, searchCriteria : String, lang : String, matchAll : Boolean) : String {
        Log.d(TAG," createUri starts")

        var uri = Uri.parse(baseUrl).
        buildUpon().
        appendQueryParameter("tags", searchCriteria).
        appendQueryParameter("tagmode", if(matchAll) "ALL" else "ANY" ).
        appendQueryParameter("lang", lang).
        appendQueryParameter("format", "json").
        appendQueryParameter("nojsoncallback", "1").
        build()

        return uri.toString()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(TAG,"onCreateOptionsMenu called")
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG,"onOptionsItemSelected caqlled")
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if(status == DownloadStatus.OK) {
            Log.d(TAG,"onDownloadComplete called")
            val getFlickerJsonData = GetFlickerJsonData(this)
            getFlickerJsonData.execute(data)
        } else {
            Log.d(TAG,"onDownloadComplete failed with status $status. Error message is: $data")
        }
    }

    //new interfaces getFlickerJson gita
    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG,".onDataAvailable called")
        flickerRecyclerViewAdapter.loadNewData(data)
        Log.d(TAG,".onDataAvailable ends")
    }

    override fun onError(exception: Exception) {
        Log.e(TAG,".onError called wit h${exception.message}")
    }
}
