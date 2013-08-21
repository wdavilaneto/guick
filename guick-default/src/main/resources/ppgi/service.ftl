/*
 * Esse arquivo pertence a Petrobras e nao pode ser utilizado fora
 * dessa empresa sem previa autorizacao.
 */
package br.com.petrobras.ppgi.ca.facade

import br.com.petrobras.ppgi.ca.domain.UserProfile
import br.com.petrobras.security.ISecurityContext
import br.com.petrobras.security.management.basic.IContextManager
import br.com.petrobras.security.management.information.IInformationValueManager
import br.com.petrobras.security.model.IContext
import br.com.petrobras.security.model.${clazz.name}
import br.com.petrobras.security.model.InformationValue
import groovy.transform.CompileStatic

/**
 *  Classe Facade da entidade ${clazz.name}
 **/
@CompileStatic
class ${clazz.name}Service extends CaServiceBase {

    public ${clazz.name} get(String id) {
<#if clazz.name == "InformationValue">
        def all = securityContext.contextManager.findAll()
        if (all == null) {
            return null
        }
        IContext contextsInfo = securityContext.contextManager.findAll()[0]
        List<InformationValue> informationValues = securityContext.informationValueManager.findAll(contextsInfo.getId())
        for (def informationValue : informationValues) {
            if (informationValue.getId().equals(id)) {
                return informationValue;
            }
        }
        return null;
<#else>
        return securityContext.get${clazz.name}Manager().find(id)
</#if>
    }

<#if clazz.name != "User">
<#if clazz.name == "InformationValue">
    public List<${clazz.name}> findAll(List<String> ids) {
        def all = securityContext.contextManager.findAll()
        if (all == null) {
            return null
        }
        IContext contextsInfo = securityContext.contextManager.findAll()[0]
        return securityContext.get${clazz.name}Manager().findAll(contextsInfo.getId())
<#else>
    public List<${clazz.name}> findAll() {
        return securityContext.get${clazz.name}Manager().findAll()
</#if>
    }
</#if>

<#if clazz.name != "User">
    public void create(${clazz.name} dto) {
        securityContext.get${clazz.name}Manager().save(dto)
    }

    public void remove(${clazz.name} dto) {
        securityContext.get${clazz.name}Manager().remove(dto)
    }

    public void update(${clazz.name} dto) {
        securityContext.get${clazz.name}Manager().update(dto)
    }
<#else>
    public List<${clazz.name}> findAll(InformationValue informationValue) {
        // TODO triangular com todos os papeis (4 select)
        securityContext.getUserRoleAuthorizationManager().findAllWithRole("VALIDADOR");
        securityContext.getUserRoleAuthorizationManager().findAllWithRole("EDITOR");
        securityContext.getUserRoleAuthorizationManager().findAllWithRole("ADMINISTRADOR");
        securityContext.getUserRoleAuthorizationManager().findAllWithRole("VISUALIZADOR");

        return null;
    }

    public void save(UserProfile userProfile) {
        //TODO
    }

    public void remove(UserProfile userProfile){
        //TODO
    }

    public void update(UserProfile userProfile) {
        //TODO
    }


</#if>


}
