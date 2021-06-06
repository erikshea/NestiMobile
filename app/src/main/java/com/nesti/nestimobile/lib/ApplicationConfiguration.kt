package com.nesti.nestimobile.lib

import org.dom4j.Node
import org.dom4j.io.SAXReader

class ApplicationConfiguration {
    private val configuration:org.dom4j.Document
    private val configurationRootTag = "configuration"

    constructor(assetManager:android.content.res.AssetManager){
        val inputStream = assetManager.open("configuration.xml")
        configuration = SAXReader().read(inputStream)
    }

    fun getNode(xPath: String): Node {
        return configuration.selectSingleNode("//$configurationRootTag/$xPath")
    }

    fun getNodes(xPath: String): List<Node> {
        return configuration.selectNodes("//$configurationRootTag/$xPath")
    }
}