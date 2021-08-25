package com.wallstreet

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.facebook.stetho.Stetho

class Application : Application(){


    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext
        Stetho.initializeWithDefaults(this)
    }

    companion object {
        lateinit var appContext: Context
        @SuppressLint("ServiceCast")
        fun isNetConnected(): Boolean {
            val cm =
                appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            var result = false
            if (activeNetwork != null) {
                result = activeNetwork.isConnectedOrConnecting
            }
            return result
        }
    }

}