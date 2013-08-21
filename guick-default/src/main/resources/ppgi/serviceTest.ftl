/*
 * Esse arquivo pertence a Petrobras e nao pode ser utilizado fora
 * dessa empresa sem previa autorizacao.
 */
package br.com.petrobras.ppgi.ca.facade

import org.junit.Before
import org.junit.Test

/**
 *  Classe de Teste do CaService da entidade ${clazz.name}
 **/
class ${clazz.name}ServiceTest extends CaContextualizedTest {

    ${clazz.name}Service service

    @Before
    void setUp() {
        super.setUp()
        service = new ${clazz.name}Service()
        service.setSecurityContext(getSecurityContext())
    }

    void "test get by id"() {
        service.get("")
        assert false
    }

    void "test find all"() {
        service.findAll()
        assert false
    }

    void "test create"() {
        service.create(null)
        assert false
    }

    void "test remove"() {
        service.remove(null)
        assert false
    }

    void "test update"() {
        service.update(null)
        assert false
    }

}
