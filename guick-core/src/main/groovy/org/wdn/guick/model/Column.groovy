package org.wdn.guick.model

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * Created by IntelliJ IDEA.
 * User: y1z5
 * Date: 10/02/12
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
class Column {

    Long id;
    String name
    String type
    Long length
    Long precision
    Long scale
    boolean nullable = true
    Long position
    String comment
    boolean unique = false

    // Bidirectional RelationShips
    RelationshipProperty simpleProperty

    // Bi-directional relations ..
    Table table

    List<String> checkValues

    public boolean isUpdatable() {
        return true; // TODO
    }

    public boolean isInsertable() {
        return true; // TODO
    }

    public String getPrefix() {
        if (!table) {
            return null;
        }
        String otherPrefixes = table.name.split("_")[0];
        if (name.split("_")[0] == otherPrefixes) {
            return otherPrefixes;
        }
        if (!isKey()) {
            otherPrefixes = table.getPk()[0].name.split("_")[0];
            if (name.split("_")[0] == otherPrefixes) {
                return otherPrefixes;
            }
        }

        return null;
    }

    public boolean isKey() {
        return position != null;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(name).
                append(table.owner).
                append(table.name).
                toHashCode();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Column rhs = (Column) obj;
        return new EqualsBuilder()
                .append(name, rhs.name)
                .append(table.name, rhs.table.name)
                .append(table.owner, rhs.table.owner)
                .isEquals();
    }

    @Override
    String toString() {
        return "Column@[Table:$table, column:$name ]";
    }
}
