package br.com.petrobras.ppgi.ca.facade

import br.com.petrobras.security.ISecurityContext

abstract class CaServiceBase {

    ISecurityContext securityContext = null;

    public void setSecurityContext(ISecurityContext context){
        this.securityContext = context
    }

}
