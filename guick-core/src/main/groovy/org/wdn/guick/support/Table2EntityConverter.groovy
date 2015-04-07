package org.wdn.guick.support

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.wdn.guick.model.*

import javax.annotation.Resource

/**
 * Classe responsavel por ler um conjunto de tabelas e gerar as entidades ( modelo) da aplicacao
 * User: wdavilaneto
 */
@Component
@Scope("prototype")
class Table2EntityConverter {

    @Resource
    private MetadataFactory metadataFactory;

    /**
     * Gera uma lista de entidades (em memoria apenas) dado uma lista de tabelas
     * @param tables
     * @return
     */
    public List<Entity> generateModel(List<Table> tables) {
        List<Entity> entityList = new ArrayList<Entity>(0)

        for (Table table : tables) {
            if (!table.isNMRelationShip()) {
                this.createEntity(entityList, table)
            }
        }

        processEntityInheritances(entityList)

        proccessComplexProperties(entityList)

        for (Table table : tables) {
            if (table.isNMRelationShip()) {
                try {
                    metadataFactory.createComplexProperty(table)
                } catch (Exception e) {
                    // ignore
                }
            }
        }

        processEmbadableEntityes(entityList)

        generateEnums(entityList)

        return entityList
    }

    private void generateEnums(List<Entity> entities) {
        List<EnumClass> enumList = new ArrayList<EnumClass>()
        EnumClass enumeration
        List<RelationshipProperty> propertiesToRemove = new ArrayList<RelationshipProperty>(0)

        for (Entity entity : entities) {
            for (RelationshipProperty simpleProperty : entity.properties) {
                if (simpleProperty.column.checkValues) {
                    enumeration = metadataFactory.createEnum(simpleProperty)
                    enumList.add(enumeration)
                    propertiesToRemove.add(simpleProperty)
                    entity.getEnums().add(enumeration)
                }
            }
            if (!propertiesToRemove.empty) {
                entity.properties.removeAll(propertiesToRemove);
                propertiesToRemove.clear();
            }
        }
    }

    private void proccessComplexProperties(List<Entity> entityList) {
        for (Entity entity : entityList) {
            // Loop para adicionar propiedades complexas
            for (Constraint constraint : entity.table.getConstraints()) {
                // Caso nao seja a contraint da Heranca adicionar propriedade complexa
                if (!isInheritenceConstraint(constraint, entity.table)) {
                    metadataFactory.createComplexProperty(entity, constraint)
                }
            }
        }
    }

    private void processEmbadableEntityes(List<Entity> entityList) {
        List<Entity> entitiesWithEmbeddedId = new ArrayList<Entity>()
        for (Entity entity : entityList) {
            if (entity.embeddedId != null) {
                entitiesWithEmbeddedId.add(entity);
            }
        }
        for (Entity embebedEntity : entitiesWithEmbeddedId) {
            // remove da classe pai as propriedades que ja existem na embaddable
            for (RelationshipProperty sProperty : embebedEntity.embeddedId.getProperties()) {
                embebedEntity.getProperties().remove(sProperty);
            }
            for (ComplexProperty cProperty : embebedEntity.embeddedId.getComplexProperties()) {
                embebedEntity.getComplexProperties().remove(cProperty);
            }
            // remover das classes que se relacionam com esta a relacao com a embeddable
            for (Entity entity : entityList) {
                List<ComplexProperty> complexPropertyToRemove = new ArrayList<ComplexProperty>()
                for (ComplexProperty cProperty : entity.getComplexProperties()) {
                    if (cProperty.referedEntity == embebedEntity.embeddedId) {
                        complexPropertyToRemove.add(cProperty);
                    }
                }
                if (!complexPropertyToRemove.isEmpty()) {
                    entity.complexProperties.removeAll(complexPropertyToRemove);
                }
            }
        }
    }

    private void processEntityInheritances(List<Entity> entityList) {
        for (Entity entity : entityList) {
            if (entity.table.inheritanceTable != null && !entity.isEmbeddable()) {
                if (entity.table.inheritanceTable.entity.embeddable) {
                    entity.parent = entity.table.inheritanceTable.entity.embeddedFor
                    entity.table.inheritanceTable.entity.embeddedFor.childrens.add(entity)
                } else {
                    entity.parent = entity.table.inheritanceTable.entity
                    entity.table.inheritanceTable.entity.childrens.add(entity)
                }
            }
        }
    }

    private void createEntity(List<Entity> entityList, Table table) {
        Entity entity = metadataFactory.createEntity(table)

        for (Column column : table.getColumns()) {
            // caso a referida coluna nao faça parte de chave..
            if (!table.getPk().contains(column)) {
                metadataFactory.createSimpleProperty(entity, column);
            }
        }
        // Se nao tiver heranca preciso processar os campos chave
        if (table.inheritanceTable == null) {
            if (table.pk.size() == 1) {
                RelationshipProperty property = metadataFactory.createSimpleProperty(entity, table.pk[0])
                // removemos da lista de propriedades e passamo-la para o campo ID
                entity.properties.remove(property);
                entity.id = property
                // redefinimos o nome do campo
                property.name = "id";
            } else {
                Entity entityPk = metadataFactory.createEmbeddedClass(entity)
                for (Column column : table.pk) {
                    // A principio Cada coluna eh uma propriedade simples..
                    RelationshipProperty property = metadataFactory.createSimpleProperty(entityPk, column)
                    // remove ja que a propriedade sera migrada para entity pk ..
                    entity.properties.remove(property);
                }
                entityList.add(entityPk)
            }
        }
        entityList.add(entity)
    }

    private isInheritenceConstraint(Constraint constraint, Table table) {
        return (constraint.referedTable.equals(table.inheritanceTable))
    }

}
