package com.apputlility

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder

open class UtilityApplication : Application() {


    companion object {
        var gson: Gson? = null
    }

    override fun onCreate() {
        super.onCreate()
        initGson()
    }

    private fun initGson() {

        gson = GsonBuilder()
            .setLenient()
            .create()

    }

}