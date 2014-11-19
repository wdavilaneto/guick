package org.wdn.guick.model

import org.apache.commons.lang.builder.HashCodeBuilder
import org.apache.commons.lang.builder.EqualsBuilder

/**
 */
class EnumValue {

    String name
    String value

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(name).
                append(value).
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
        EnumValue rhs = (EnumValue) obj;
        return new EqualsBuilder()
                .append(name, rhs.name)
                .append(value, rhs.value)
                .isEquals();
    }

    public String getName(){
        return name == null ? value : name
    }

    public String toString() {
        return value
    }
}
