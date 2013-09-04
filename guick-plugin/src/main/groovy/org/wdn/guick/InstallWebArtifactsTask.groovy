package org.wdn.guick

/**
 * Created with IntelliJ IDEA.
 * User: y1z5
 * Date: 03/09/13
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
class InstallWebArtifactsTask extends GuickCoreTask {
    @Override
    def onExecute() {
        guickEngine.runEngine("installWebArtifacts");
    }
}
