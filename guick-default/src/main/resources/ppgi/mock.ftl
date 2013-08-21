/*
 * Esse arquivo pertence a Petrobras e nao pode ser utilizado fora
 * dessa empresa sem previa autorizacao.
 */

package br.com.petrobras.ppgi.ca.facade.mock

import br.com.petrobras.ppgi.ca.domain.UserProfile
import br.com.petrobras.security.ISecurityContext
import br.com.petrobras.security.management.basic.IContextManager
import br.com.petrobras.security.management.information.IInformationValueManager
import br.com.petrobras.security.model.IContext
import br.com.petrobras.security.model.${clazz.name}
import br.com.petrobras.security.model.InformationValue

/**
 *  Classe Mock para Facade da entidade ${clazz.name}
 **/
class ${clazz.name}FacadeMock {

    public ${clazz.name} get(String id) {
<#if clazz.name == "InformationValue">
        return null
<#else>
        return null
</#if>
    }

<#if clazz.name != "User">
<#if clazz.name == "InformationValue">
    public List<${clazz.name}> findAll(List<String> ids) {
        return null
<#else>
    public List<${clazz.name}> findAll() {
        return null
</#if>
    }
</#if>

<#if clazz.name != "User">
    public void create(${clazz.name} dto) {

    }

    public void remove(${clazz.name} dto) {

    }

    public void update(${clazz.name} dto) {

    }
<#else>
    public List<${clazz.name}> findAll(InformationValue informationValue) {
        // TODO triangular com todos os papeis (4 select)
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