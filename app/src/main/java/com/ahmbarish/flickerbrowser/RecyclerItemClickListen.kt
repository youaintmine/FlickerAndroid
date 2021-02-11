package com.ahmbarish.flickerbrowser

import android.content.Context
import android.text.method.Touch.onTouchEvent
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListen (context : Context, recyclerView : RecyclerView, private val listen: OnRecyclerClickListener) : RecyclerView.SimpleOnItemTouchListener() {
     private val TAG = "RecyclerItemClickListen"

    interface OnRecyclerClickListener {
        fun onItemClick(view: View?, pos: Int)
        fun onItemHold(view : View, pos : Int)
    }
    //add gesture detector
    private val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(TAG,".onSingleTapUp: starts")
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            Log.d(TAG,".onSingleTapup calling listener.onItemClick")
            listen.onItemClick(childView!!, recyclerView.getChildAdapterPosition(childView))
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Log.d(TAG, ".onLongPress : starts")
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            Log.d(TAG,".onLongPress calling listener.onItemClick")
            listen.onItemHold(childView!!, recyclerView.getChildAdapterPosition(childView))
        }
    })
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG,"onInterceptTouchEvent : starts")
        val result = gestureDetector.onTouchEvent(e)
//        return super.onInterceptTouchEvent(rv, e)
        return result
    }
}