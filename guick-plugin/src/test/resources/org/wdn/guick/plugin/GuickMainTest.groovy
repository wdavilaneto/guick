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

    private static final DEFAULT_PROJECT_ROOT = "../wdavilaneto"


    void testMigrateToDb() {
        GuickMain.main("migrateToDb",DEFAULT_PROJECT_ROOT)
        assert true
    }

    void testInstallWebArtifacts() {
        GuickMain.main("installWebArtifacts",DEFAULT_PROJECT_ROOT)
        assert true
    }

    void testMigrateToJava() {
        GuickMain.main("migrateToDomain",DEFAULT_PROJECT_ROOT)
        assert true
    }

    @Test
    void testGenerateCrud() {
        GuickMain.main("generateCrud",DEFAULT_PROJECT_ROOT)
        assert true
    }

}
