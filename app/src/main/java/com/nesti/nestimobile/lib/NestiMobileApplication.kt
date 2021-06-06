package com.nesti.nestimobile.lib

import android.app.Application
import android.content.res.Configuration
import org.dom4j.Node
import org.dom4j.io.SAXReader

//import org.dom4j.io.SAXReader;

class NestiMobileApplication : Application() {
    lateinit var configuration:ApplicationConfiguration

    override fun onCreate() {
        super.onCreate()
        configuration = ApplicationConfiguration(assets)
    }
}