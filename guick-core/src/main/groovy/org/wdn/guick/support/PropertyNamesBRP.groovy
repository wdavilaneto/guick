package org.wdn.guick.support

import org.wdn.guick.model.Project
import org.wdn.guick.model.Clazz
import org.wdn.guick.support.PatternConverterFacade
import org.springframework.stereotype.Component
import org.wdn.guick.model.RelationshipProperty

/**
 * Implementacao de BusinessRulesProcessor que acerta
 * possiveis incoerencias sobre os property names das entidades.
 * Atualmente apenas o tratamento de propriedades que ja possam existir com o
 * mesmo nome no Parent que estão sendo tratadas...
 *
 */
@Component
class PropertyNamesBRP implements IBusinessRulesProcessor {

    /**
     * {@inheritDoc}
     */
    @Override
    Project doProcess(Project project) {

        RelationshipProperty propertyToChangeName
        for (def entity : project.entities) {
            if (entity.parent != null) {
                for (Clazz property : entity.parent.properties) {
                    propertyToChangeName = getPropertyByName( entity.properties , property.name)
                    if (propertyToChangeName != null) {
                        propertyToChangeName.name = PatternConverterFacade.getBeanPatternWithPrefix(propertyToChangeName.column.name)
                    }
                }
            }
        }
        return null
    }

    private RelationshipProperty getPropertyByName(List<Clazz> properties, name) {
        for (RelationshipProperty prop : properties) {
            if (prop.name.equals(name)) {
                return prop
            }
        }
        return null
    }
}
