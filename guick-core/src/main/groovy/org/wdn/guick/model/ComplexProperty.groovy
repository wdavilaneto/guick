package org.wdn.guick.model

import org.apache.commons.lang.builder.EqualsBuilder
import org.wdn.guick.support.PatternConverterFacade

public class ComplexProperty extends Clazz {

    Constraint constraint
    Entity referedEntity
    Table nmTable
    List<Validation> validations = new ArrayList<Validation>()
    String mappedBy;

    public ComplexProperty(Entity referedEntity) {
        this.referedEntity = referedEntity;
        this.name = PatternConverterFacade.getInstanceBeanName(referedEntity.getName()) + "Collection"
    }

    public ComplexProperty(Constraint constraint, Entity referedEntity) {
        this.constraint = constraint;
        this.referedEntity = referedEntity;
        this.name = PatternConverterFacade.getInstanceBeanName(referedEntity.getName())
    }

    public boolean isOneToMany() {
        return (constraint == null);
    }

    public boolean isManyToOne() {
        return (constraint != null && !isManyToMany())
    }

    public boolean isManyToMany() {
        return (nmTable != null);
    }

    public Constraint getInverseConstraint() {
        return nmTable.constraints[0] != constraint ? nmTable.constraints[0] : nmTable.constraints[1]
    }

    public String getType() {
        return referedEntity.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .isEquals();
    }

}
