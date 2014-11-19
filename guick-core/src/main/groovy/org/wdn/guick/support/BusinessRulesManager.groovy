package org.wdn.guick.support

import org.wdn.guick.model.Project
import org.springframework.stereotype.Service

import javax.annotation.Resource

/**
 * classe que que executa Regras de negocio de validacao e auto enrriquecimento de metadado
 */
@Service
class BusinessRulesManager {

    @Resource
    List<IBusinessRulesProcessor> processorList

    public Project handleRules(Project project) {
        for (IBusinessRulesProcessor iProcessor : processorList) {
            iProcessor.doProcess(project)
        }
        return project
    }

}
