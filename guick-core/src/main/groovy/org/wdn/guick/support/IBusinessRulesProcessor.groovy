package org.wdn.guick.support

import org.wdn.guick.model.Project

/**
 * Interface "command" cujas implementacoes serao executadas para validar e/ou transformar metadado de acordo com
 * as regras de negocio de transformação definidas nas implementacoes especificas desta interface
 * AKA: Imeplemente a regra de negocio de transformacao de metadado como um bean de spring que sera chamado automaticamente
 *
 */
interface IBusinessRulesProcessor {

    /**
     * Metodo command que sera executado pelo motor de regra de tranformacao de metadado
     * @param project
     * @return
     */
    public Project doProcess(Project project);

}
