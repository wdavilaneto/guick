/*
 * Esse arquivo pertence a Petrobras e nao pode ser utilizado fora
 * dessa empresa sem previa autorizacao.
 */
package br.com.petrobras.ppgi.ca.facade

import br.com.petrobras.security.ISecurityContext
import br.com.petrobras.security.model.${clazz.name}

/**
 *  Classe Facade da entidade ${clazz.name}
 **/
class ${clazz.name}Facade {

    private ISecurityContext securityContext = ISecurityContext.getContext();

    public ${clazz.name} get(String id) {
<#if clazz.name != "IContext">
        securityContext.get${clazz.name}Manager().find(id)
<#else>
        securityContext.getContextManager().find(id)
</#if>
    }

    public ${clazz.name} find(List<String> ids) {
<#if clazz.name != "IContext">
        securityContext.get${clazz.name}Manager().find(ids)
<#else>
        securityContext.getContextManager().find(ids)
</#if>
    }

    public List<${clazz.name}> findAll() {
<#if clazz.name != "IContext">
        securityContext.get${clazz.name}Manager().findAll()
<#else>
        securityContext.getContextManager().findAll()
</#if>
    }

    public void create(${clazz.name} dto) {
<#if clazz.name != "IContext">
        securityContext.get${clazz.name}Manager().save(dto)
<#else>
        securityContext.getInformationManager().save(dto)
</#if>
    }

    public void remove(${clazz.name} dto) {
<#if clazz.name != "IContext">
        securityContext.get${clazz.name}Manager().remove(dto)
<#else>
        securityContext.getInformationManager().remove(dto)
</#if>
    }

    public void update(${clazz.name} dto) {
<#if clazz.name != "IContext">
        securityContext.get${clazz.name}Manager().update(dto)
<#else>
        securityContext.getInformationManager().update(dto)
</#if>
    }
}
