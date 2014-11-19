package org.wdn.guick.support

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.wdn.guick.model.*

import javax.annotation.Resource

/**
 * Created with IntelliJ IDEA.
 * User: y1z5
 * Date: 29/05/12
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
@Component
class MetadataFactory {

    private static final Logger logger = LoggerFactory.getLogger(MetadataFactory.class);

    private static final PRIMARY_KEY_SUFFIX = "Id"

    @Resource
    Project currentProject;


    public Entity createEntity(Table table = null) {
        Entity entity = new Entity()
        entity.setProject(currentProject)
        entity.setPackage(currentProject.group + ".entity")
        entity.setType(entity.name)

        if (table != null) {
            entity.setTable(table)
            table.entity = entity
            entity.name = PatternConverterFacade.getBeanName(table.name)
        }

        return entity
    }

    public Entity createEmbeddedClass(Entity entity = null) {
        Entity embbededId = new Entity()
        embbededId.name = entity.name + PRIMARY_KEY_SUFFIX
        embbededId.setProject(currentProject)
        embbededId.setPackage(currentProject.group + ".entity")
        embbededId.setType(embbededId.name)
        embbededId.embeddedFor = entity
        entity.embeddedId = embbededId
        return embbededId
    }

    public EnumClass createEnum(RelationshipProperty simpleProperty = null) {
        EnumClass instance = new EnumClass()
        instance.setProject(currentProject)
        instance.setPackage(currentProject.group + "entity.domain")
        if (simpleProperty != null) {
            def lastNameColumn = PatternConverterFacade.getBeanName(PatternConverterFacade.getLastNameColumn(simpleProperty.column.name))
            instance.name = lastNameColumn + simpleProperty.entity?.name + "Enum"
            instance.simpleProperty = simpleProperty;
            EnumValue enumValue
            for (def check : simpleProperty.column.checkValues) {
                enumValue = new EnumValue(name: (check.isNumber() ? "VALOR_" + check : check), value: check)
                instance.checkValues.add(enumValue)
            }
        }
        return instance
    }

    public RelationshipProperty createSimpleProperty(Entity entity, Column column) {
        RelationshipProperty property = new RelationshipProperty();
        property.project = currentProject
        property.setColumn(column)
        property.entity = entity
        // bidirectional relationship
        column.simpleProperty = property

        property.name = getPropertyName(getAllRelationShipProperties(entity), PatternConverterFacade.columnToPropertyName(column))
        entity.properties.add(property)
        return property
    }

    def createComplexProperty(Entity entity, Constraint constraint) {

        if (constraint?.getReferedTable() != null) {

            // Se o cara for NAO for um entityID OU for um campo que eh parte da CHAVE do EntityID .. processa a chave composta
            if ((!entity.isEmbeddable()) || (entity.isEmbeddable() && entity.table.pk.containsAll(constraint.thisSideColumns))) {
                // ToOne Complex property
                ComplexProperty complexProperty = new ComplexProperty(constraint, constraint.getReferedTable().entity);
                complexProperty.project = currentProject
                complexProperty.name = getPropertyName(getAllComplexProperties(entity), complexProperty.name)
                if (entity.containsPropertyName(complexProperty.name)) {
                    complexProperty.name += "Entity"
                }
                entity.getComplexProperties().add(complexProperty)

                // ToMany Property create
                ComplexProperty referedProperty = new ComplexProperty(entity)
                referedProperty.project = currentProject
                referedProperty.name = getPropertyName(getAllComplexProperties(complexProperty.referedEntity), referedProperty.name)
                complexProperty.referedEntity.getComplexProperties().add(referedProperty);

                for (ColumnPair parDeColuna : constraint.getColumnPairs()) {
                    try {
                        entity.getProperties().remove(parDeColuna.getColuna().simpleProperty)
                    } catch (ex){
                        logger.error( "Invalid Column Pair: " + parDeColuna , ex);
                    }

                }
                return [complexProperty: complexProperty, referedProperty: referedProperty]
            }
            return null
        }
    }

    def createComplexProperty(Table table) {

        Entity thisEntity = table.constraints[0].getReferedTable().entity
        Entity otherEntity = table.constraints[1].getReferedTable().entity


        ComplexProperty complexProperty = new ComplexProperty(table.constraints[0], otherEntity);
        complexProperty.project = currentProject
        complexProperty.name = getPropertyName(getAllComplexProperties(otherEntity), complexProperty.name) + "Collection"
        thisEntity.getComplexProperties().add(complexProperty);
        complexProperty.nmTable = table

        ComplexProperty referedProperty = new ComplexProperty(table.constraints[1], thisEntity);
        referedProperty.project = currentProject
        referedProperty.name = getPropertyName(getAllComplexProperties(thisEntity), referedProperty.name) + "Collection"
        otherEntity.getComplexProperties().add(referedProperty);
        referedProperty.nmTable = table

        table.entity = null

        return [complexProperty: complexProperty, referedProperty: referedProperty]
    }

    private List<RelationshipProperty> getAllRelationShipProperties(Entity entity) {
        List<RelationshipProperty> props = new ArrayList<RelationshipProperty>(entity.properties)
        if (entity.parent != null) {
            props.addAll(entity.parent.properties)
        }
        return props
    }

    private List<ComplexProperty> getAllComplexProperties(Entity entity) {
        List<ComplexProperty> props = new ArrayList<ComplexProperty>(entity.complexProperties)
        if (entity.parent != null) {
            props.addAll(entity.parent.complexProperties)
        }
        return props
    }

    private String getPropertyName(List<Clazz> properties, String propertyName, int counter = 0) {
        String name = propertyName + (counter == 0 ? "" : counter);
        if (!hasPropertyWithName(properties, name)) {
            return name
        } else {
            return getPropertyName(properties, propertyName, counter + 1);
        }
    }

    private boolean hasPropertyWithName(List<Clazz> properties, name) {
        for (Clazz prop : properties) {
            if (prop.name.equals(name)) {
                return true
            }
        }
        return false
    }

}