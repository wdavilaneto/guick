package org.wdn.guick.loader

import org.wdn.guick.model.Table

/**
 * Mapper que obtem do banco de dados Oracle as informacoes relacionais de um dado owner
 */
interface TableMapper {

    /**
     * Obtem lista de tabelas e colunas de um dado owner
     * @param owner
     * @return
     */
    List<Table> findTableAndColumns(String owner);

    /**
     * Obtem lista de constraints de um dado owner
     * @return
     */
    List<Map> findContraints(String owner);

}