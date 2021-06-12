package com.nesti.nestimobile.lib

import org.dom4j.Node
import org.dom4j.io.SAXReader

/**
 * provides access to values stored in configuration file
 */
class ApplicationConfiguration {
    private val configuration:org.dom4j.Document
    private val configurationRootTag = "configuration"

    /**
     * @param assetManager allows relative access to file in assets dir
     */
    constructor(assetManager:android.content.res.AssetManager){
        val inputStream = assetManager.open("configuration.xml")
        configuration = SAXReader().read(inputStream)
    }

    /**
     * get a single node or attribute from XML
     */
    fun getNode(xPath: String): Node {
        return configuration.selectSingleNode("//$configurationRootTag/$xPath")
    }

    /**
     * get multiple nodes or attributes from XML
     */
    fun getNodes(xPath: String): List<Node> {
        return configuration.selectNodes("//$configurationRootTag/$xPath")
    }
}