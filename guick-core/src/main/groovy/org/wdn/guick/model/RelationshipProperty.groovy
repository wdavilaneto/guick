package org.wdn.guick.model

import org.wdn.guick.support.PatternConverterFacade
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * Classe de modelo que representa uma propriedade simples de uma classe ou entidade
 * Por propriedade simples entende-se propriedades do "tipo" ou de uma "Classe" da própria linguagem de programacao
 * Ex: String, Long, Date e etc
 * @author Y1Z5
 */
public class RelationshipProperty extends Clazz {

    Column column;
    Boolean nullable = null;
    List<Validation> validations = new ArrayList<Validation>()

    // Bidiretional Association
    Entity entity

    @Override
    public String getType() {
        return PatternConverterFacade.getBeanType(column.type)
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                appendSuper(super.hashCode()).
                append(column).
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
        RelationshipProperty rhs = (RelationshipProperty) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(column, rhs.column)
                .isEquals();
    }

}
