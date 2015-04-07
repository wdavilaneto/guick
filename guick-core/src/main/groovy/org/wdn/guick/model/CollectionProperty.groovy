package org.wdn.guick.model

import org.apache.commons.lang.builder.EqualsBuilder

/**
 * Created with IntelliJ IDEA.
 * User: wdavilaneto
 * Date: 05/06/12
 * Time: 10:23
 * To change this template use File | Settings | File Templates.
 */
class CollectionProperty extends Clazz {

    String collectionType
    String collectionInterface

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
                .append(collectionType, rhs.collectionType)
                .append(collectionInterface, rhs.collectionInterface)
                .isEquals();
    }

}
