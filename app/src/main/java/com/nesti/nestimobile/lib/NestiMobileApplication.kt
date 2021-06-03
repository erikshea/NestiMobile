package com.nesti.nestimobile.lib

import android.app.Application
import android.content.res.Configuration
import org.dom4j.Node
import org.dom4j.io.SAXReader

//import org.dom4j.io.SAXReader;

class NestiMobileApplication : Application() {
    lateinit var configuration:ApplicationConfiguration

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    override fun onCreate() {
        super.onCreate()
        configuration = ApplicationConfiguration(assets)
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    override fun onLowMemory() {
        super.onLowMemory()
    }
}