package org.wdn.guick.model

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

/**
 */
class EnumClass extends Clazz {

    List<EnumValue> checkValues = new ArrayList<EnumValue>()

    RelationshipProperty simpleProperty

    public String getPackage() {
        return project.group+ "." + project.getAcronym() + ".domain";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                appendSuper(super.hashCode()).
                append(simpleProperty).
                toHashCode();
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
        EnumClass rhs = (EnumClass) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(simpleProperty, rhs.simpleProperty)
                .isEquals();
    }
}
