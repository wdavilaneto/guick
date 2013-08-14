/*
 * Esse arquivo pertence a Petrobras e nao pode ser utilizado fora
 * dessa empresa sem previa autorizacao.
 */
package br.com.petrobras.ppgi.ca.facade

import org.junit.Test

/**
 *  Classe Facade da entidade ${clazz.name}
 **/
class ${clazz.name}FacadeTest extends CaContextualizedTest {

    ${clazz.name}Facade facade = new ${clazz.name}Facade()

    @Test
    void "test get by id"() {
        facade.get("")
        assert false
    }

    @Test
    void "test get by id list"() {
        facade.find(["a" , "b"])
        assert false
    }

    @Test
    void "test find all"() {
        facade.findAll()
        assert false
    }

    @Test
    void "test create"() {
        facade.create(null)
        assert false
    }

    @Test
    void "test remove"() {
        facade.remove(null)
        assert false
    }

    @Test
    void "test update"() {
        facade.update(null)
        assert false
    }

}
