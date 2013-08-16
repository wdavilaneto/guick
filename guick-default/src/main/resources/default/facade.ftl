/*
 * Esse arquivo pertence a Petrobras e nao pode ser utilizado fora
 * dessa empresa sem previa autorizacao.
 */
package br.com.petrobras.ppgi.ca.facade

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
class ${clazz.name}Facade {

    private ISecurityContext securityContext = ISecurityContext.getContext()
    private IContextManager contextManager
    private IInformationValueManager informationValueManager

    public ${clazz.name}Facade() {
        contextManager = securityContext.getContextManager()
        informationValueManager = securityContext.getInformationValueManager()
    }

    public ${clazz.name} get(String id) {
<#if clazz.name == "InformationValue">
        def all = contextManager.findAll()
        if (all == null) {
            return null
        }
        IContext contextsInfo = contextManager.findAll()[0]
        List<InformationValue> informationValues = informationValueManager.findAll(contextsInfo.getId())
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

<#if clazz.name == "InformationValue">
    public List<${clazz.name}> findAll(List<String> ids) {
        def all = contextManager.findAll()
        if (all == null) {
            return null
        }
        IContext contextsInfo = contextManager.findAll()[0]
        return securityContext.get${clazz.name}Manager().findAll(contextsInfo.getId())
<#else>
    public List<${clazz.name}> findAll() {
        return securityContext.get${clazz.name}Manager().findAll()
</#if>
    }

    public void create(${clazz.name} dto) {
        <#if clazz.name == "User">//</#if>securityContext.get${clazz.name}Manager().save(dto)
    }

    public void remove(${clazz.name} dto) {
        <#if clazz.name == "User">//</#if>securityContext.get${clazz.name}Manager().remove(dto)
    }

    public void update(${clazz.name} dto) {
        <#if clazz.name == "User">//</#if>securityContext.get${clazz.name}Manager().update(dto)
    }
}
