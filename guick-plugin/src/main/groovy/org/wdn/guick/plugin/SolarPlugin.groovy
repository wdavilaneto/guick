package org.wdn.guick.plugin

import org.wdn.guick.core.ResourceReader

/**
 * Created with IntelliJ IDEA.
 * User: wdavilaneto
 * Date: 06/09/13
 * Time: 18:11
 * To change this template use File | Settings | File Templates.
 */
class SolarPlugin extends GuickMain {

    public static void main(String[] args) {
        ResourceReader reader = new ResourceReader()
        def resource = reader.getReader("solar/data.xml")
//        def data = new BufferedReader(reader.getReader("solar/data.xml"))
//        data.readLines().each {
//            if (it.contains('<input id="svn.remote.loc"')){
//                println it.value
//            }
//        }

        def values = new XmlSlurper(false,false).parse(resource)
        values.depthFirst().grep { it.@id == 'svn.remote.loc' }.'@value'*.text().each {
            println it
        }
    }
}
