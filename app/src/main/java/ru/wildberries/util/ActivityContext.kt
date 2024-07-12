package ru.wildberries.util

import android.content.Context

class ActivityContext {
    companion object {

        private lateinit var _context: Context
        val context: Context
            get() = _context

        fun setActivityContext(context: Context) {
            this._context = context
        }
    }
}