package org.wdn.guick.model

import org.apache.commons.lang.builder.EqualsBuilder

public class Constraint {

    String name;
    List<ColumnPair> columnPairs = new LinkedList<ColumnPair>();
    Table table;
    Table referedTable;
    ConstraintType tipo;

    public List<Column> getThisSideColumns() {
        List<Column> columns = new ArrayList<Column>()
        for (ColumnPair pair : columnPairs) {
            columns.add(pair.coluna)
        }
        return columns;
    }

    public List<Column> getReferedSideColumns() {
        List<Column> columns = new ArrayList<Column>()
        for (ColumnPair pair : columnPairs) {
            columns.add(pair.colunaReferenciada)
        }
        return columns;
    }

    public boolean isSingleColumn() {
        return (columnPairs.size() == 1);
    }

    public ColumnPair getSingleColumnPair() {
        if (isSingleColumn()) {
            return columnPairs.get(0);
        }
        return null;
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
        Constraint rhs = (Constraint) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(name, rhs.name)
                .append(table, rhs.table)
                .append(referedTable, rhs.referedTable)
                .isEquals();
    }


}
