package org.wdn.guick.loader

import org.apache.ibatis.annotations.Param
import org.wdn.guick.model.Table

/**
 * Mapper que obtem do banco de dados Oracle as informacoes relacionais de um dado owner
 */
interface TableMapper {

    /**
     * Returns table informations for given parameters
     * @param owner
     * @return
     */
    List<Table> findTableAndColumns(@Param("owner") String owner);

    /**
     * Returns an list of contraints
     * @return
     */
    List<Map> findContraints(@Param("owner") String owner);

    /**
     * Returns a estimate (statistc count) quantity of tuples on a given Table
     * @return
     */
    Long count(@Param("owner") String owner, @Param("table") String table)

}