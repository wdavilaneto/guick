package org.wdn.guick.plugin

import org.junit.Test

/**
 * Created with IntelliJ IDEA.
 * User: y1z5
 * Date: 05/09/13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
class GuickMainTest {

    private static final DEFAULT_PROJECT_ROOT = "../mgp/"
    private static final GROUP = "br.mprj"
    private static final NAME = "mgp"

    void testMigrateToDb() {
        GuickMain.main("migrateToDb", DEFAULT_PROJECT_ROOT, GROUP, NAME)
        assert true
    }

    void testMigrateToDomain() {
        GuickMain.main("installGradle", DEFAULT_PROJECT_ROOT, GROUP, NAME)
        assert true
    }

    void testInstallWebArtifacts() {
        GuickMain.main("installWebArtifacts", DEFAULT_PROJECT_ROOT, GROUP, NAME)
        assert true
    }

    void testGenerateCrud() {
        GuickMain.main("generateCrud", DEFAULT_PROJECT_ROOT, GROUP, NAME)
        assert true
    }

}
