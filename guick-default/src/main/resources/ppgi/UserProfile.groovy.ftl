/*
 * Esse arquivo pertence a Petrobras e nao pode ser utilizado fora
 * dessa empresa sem previa autorizacao.
 */
package br.com.petrobras.ppgi.ca.domain

import br.com.petrobras.security.model.InformationValue
import br.com.petrobras.security.model.Role
import br.com.petrobras.security.model.User



class UserProfile extends User {

    Role role
    InformationValue context

}
