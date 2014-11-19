package org.wdn.guick.support

import org.springframework.stereotype.Component
import org.wdn.guick.model.Project

/**
 * Created with IntelliJ IDEA.
 * User: y1z5
 * Date: 15/05/12
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
@Component
class RelationalInfoBRP implements IBusinessRulesProcessor {

    @Override
    Project doProcess(Project project) {
        for (def table : project.tables) {
            for (def column : table.columns) {
                // garante o relacionamento bidirecional table <-> coluna
                if (column.table == null) {
                    column.table = table;
                }
                // limpa informacoes desnecessarias para campo do tipo String
                if (PatternConverterFacade.getBeanType(column.type).equals("String")) {
                    column.precision = null;
                    column.scale = null;
                }
                // limpa informacoes desnecessarias para campo do tipo Date
                if (PatternConverterFacade.getBeanType(column.type).equals("Date")) {
                    column.precision = null;
                    column.scale = null;
                    //column.length = null; //comentei pq preciso desse valor para o template JSF
                }
                // limpa informacoes desnecessarias para campo do tipo Long
                if (PatternConverterFacade.getBeanType(column.type).equals("Long")) {
                    //column.length = null; //comentei pq preciso desse valor para o template JSF
                    // se nao tiver informacao de precision o scale e inutil neste caso...
                    if (column.precision == null) {
                        column.scale = null
                    }
                }
            }
        }
        return project;
    }
}
