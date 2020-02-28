package app.weatherapp.utils

import android.util.Log


object Logger {
    private val TAG = "WeatherApp"
    var LOGGER: Boolean? = null

    fun showLog(value: Boolean) {
        LOGGER = value
    }

    fun error(s: String, throwable: Throwable) {
        if (LOGGER!!)
            Log.e(TAG, s, throwable)
    }

    fun e(s: String) {
        if (LOGGER!!)
            Log.e(TAG, "" + s)
    }

    fun w(s: String) {
        if (LOGGER!!)
            Log.w(TAG, "" + s)
    }

    fun i(s: String) {
        Log.i(TAG, "" + s)
    }

    fun v(s: String) {
        if (LOGGER!!)
            Log.v(TAG, "" + s)
    }

    fun d(s: String) {
        if (LOGGER!!)
            Log.d(TAG, "" + s)
    }

    fun w(string: String, e: Exception) {
        if (LOGGER!!)
            Log.w(TAG, string, e)
    }

    fun e(string: String, e: Exception) {
        if (LOGGER!!)
            Log.e(TAG, string, e)
    }
}