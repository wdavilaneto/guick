package org.wdn.guick.model

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * Classe de modelo (Metadado do projeto) que representa uma Tabela Da banco de dados
 * existente no Projeto.
 */
class Table {

    String owner;
    String name;
    String comment;
    String sequenceName;
    Table inheritanceTable;

    Long count = 0;

    //Constraint primaryKey
    List<Column> columns = new ArrayList<Column>();
    List<Constraint> constraints = new ArrayList<Constraint>();

    // relacionamento bidirencional
    Project project
    Entity entity

    /**
     * Uma tabela pode ter uma chave simples, ou seja uma coluna, ou seu chave pode ser representada
     * por um conjunto de colunas. Este metodo retorna uma lista com um ou mais valores de colunas que compoem
     * a primary key da tabela
     * @return
     */
    public List<Column> getPk() {
        List<Column> pks = new ArrayList<Column>();
        for (Column column : this.columns) {
            if (column.isKey()) {
                pks.add(column);
            }
        }
        return pks;
    }

    public List<Column> getFieldThatPointsParentPk() {
        List<Column> pks = new ArrayList<Column>();
        // Ok, esta tabela nao definiu primary key .. mas pode ter parent...
        // Busca todas as foreing keys que apontam para primary
        // Se achar, eh nossa chave  candidata
        for (Constraint cons : this.constraints) {
            if (cons.referedSideColumns[0] != null && cons.referedSideColumns[0].isKey()) {
                pks.addAll(cons.thisSideColumns[0]);
            }
        }
        return pks;
    }

    /**
     * Metodo "de negocio" que retorna se a tabela eh uma tabela de relacionamento N x M
     * @return
     */
    private Boolean _isNamRelationship;

    public boolean isNMRelationShip() {
        if (_isNamRelationship == null) {
            List<Constraint> fkContraints = constraints.each { t -> t.tipo.equals(ConstraintType.Relationship) }
            if (fkContraints.size() < 2) {
                return false;
            }
            List<Column> cols = new ArrayList<Column>()
            for (Constraint fk : fkContraints) {
                cols.addAll(fk.thisSideColumns);
            }
            if (cols.plus(pk).containsAll(columns) && columns.size() == 3) {
                _isNamRelationship = true;
            } else {
                if (pk.equals(cols)) {
                    cols = columns.clone()
                    cols.removeAll(pk)
                    for (Column col : cols) {
                        // TODO Ver padrao de infra para campos default SYSDATE
                        if (!col.name.endsWith("DT_ULT_ALTERACAO") || !col.name.endsWith("DT_INCLUSAO")) {
                            _isNamRelationship = false;
                        }
                    }
                    _isNamRelationship = true;
                }

            }
        }
        return _isNamRelationship;
    }

    def getPrefix() {
        if (project.config?.tablePrefix) {
            return project.config.tablePrefix;
        }
        if (name.split("_")[0] == owner) {
            return owner;
        }
        if (getPk()[0]) {
            getPk()[0].getPrefix();
        }
        return "";
    }

    /**
     *
     * @param project
     */
    void setProject(Project project) {
        this.project = project
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(owner).
                append(name).
                toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Table rhs = (Table) obj;
        return new EqualsBuilder()
                .append(name, rhs.name)
                .append(owner, rhs.owner)
                .isEquals();
    }

    @Override
    public String toString() {
        return name
    }
}
