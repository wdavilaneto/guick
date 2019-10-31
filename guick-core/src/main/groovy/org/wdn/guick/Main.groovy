package org.wdn.guick
/**
 *
 * User: wdavilaneto
 * Date: 9/25/13
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */
class Main {

    static void main(String[] args) {
        ExpandoMetaClass.disableGlobally()
        try {
            GuickAppliction guick = new GuickAppliction()
            guick.initialize("../generated2")
            guick.setTarget("gava/create-api").run()
            guick.setTarget("gava/crud").run()
//            guick.setTarget("angular/create-webapp").run()
//            guick.setTarget("angular/crud").run()

            guick.setTarget("stage/create-webapp").run()
            guick.setTarget("stage/crud").run()

        } catch (RuntimeException e) {
            e.printStackTrace()
        }
    }

}

//main.setTarget("stage/domain")
