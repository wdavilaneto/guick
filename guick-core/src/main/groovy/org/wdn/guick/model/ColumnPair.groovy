package org.wdn.guick.model

public class ColumnPair {

    Column coluna;
    Column colunaReferenciada;

    public ColumnPair(Column coluna, Column colunaReferenciada) {
        this.coluna = coluna;
        this.colunaReferenciada = colunaReferenciada;
    }

    @Override
    public String toString() {
        return [ this.class.name + "@['Coluna': $coluna , 'Coluna Referenciada:': $colunaReferenciada ]"]
    }

}
