package org.wdn.guick.loader

import org.apache.ibatis.session.SqlSession
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

import javax.annotation.Resource
import java.sql.SQLException
import org.wdn.guick.model.*;


/**
 * Class Command Responsavel por obter (selects) das informacoes
 *  (tabelas e constraints) contidas no Banco Relacional Oracle
 * do projeto em questao.
 */
@Component
class TableRetriever {

    @Resource
    private Project project;

    private static final Logger logger = LoggerFactory.getLogger(TableRetriever.class)

    /**
     * executa a funcao da classe Command que eh ler as informacoes das tableas
     * que contem as informacoes (tabelas e constraints) que o usuario tem acesso (schema)
     * @param project
     * @return
     */
    public List<Table> execute(SqlSession session, def user= null) {
        List<Table> tableList

        TableMapper mapper
        if (project.config?.guickConnectionInfo?.dialect == "org.hibernate.dialect.PostgreSQLDialect") {
            mapper = session.getMapper(PostgresTableMapper.class)
        } else {
            mapper = session.getMapper(TableMapper.class)
        }

        try {
            logger.info("Retrieving information from schema: " + project.config.tables )

            tableList = mapper.findTableAndColumns(project.config.tables);
            Map<String, Table> tables = new HashMap<String, Table>();

            for (Table table : tableList) {
                // Retrieve a statistic count from the given table for better euristics...
                try {
                    //table.count = mapper.count(table.owner, table.name);
                } catch (Exception e) {
                    // ignore
                }

                tables.put(table.getName(), table);
                // ajust bidirectional Association
                for (def column : table.columns) {
                    column.table = table;
                }
                table.project = project;
            }
            List<ConstraintDto> contraints = mapper.findContraints(project.config.tables);
            processTables(tables, contraints);

            for (Table table : tableList) {
                table.inheritanceTable = getInheritanceTable(table)
            }

        } finally {
            session.close()
        }
        return tableList
    }

    /**
     * metoso que faz o BIND das informacos das tableas com as informacoes das constraints para formar um
     * metadado consistente (relacionamenteo table->constraints )
     * @param tabelas
     * @param contraints
     * @throws SQLException
     * @throws Exception
     */
    private void processTables(HashMap<String, Table> tabelas, List<ConstraintDto> contraints) throws SQLException, Exception {

        Table tabelaPai, tabelaReferenciada
        Constraint constraint
        Column column, colunaReferenciada
        String lastContraintsName = null

        for (ConstraintDto dto : contraints) {

            // Obtem da lista de tabelas a tabel com o name alvo
            tabelaPai = tabelas.get((String) dto.tableName);

            // para ignorar tabelas como internas como BIN$!@#$....
            if (tabelaPai != null) {

                switch (dto.type) {
                    case 'R':
                        // !contraint["TABLE_NAME"].equals(tabelaPai?.getName())
                        if (!dto.name?.toString()?.equalsIgnoreCase(lastContraintsName)) {
                            lastContraintsName = dto.name;

                            // Instanciamos uma nova constraint ( obtem do hash via name)
                            constraint = new Constraint();

                            // FIXME p*rr@ !! referencia uma tabela sem PRIMARY KEY!!!!
                            if (tabelaPai != null) {
                                tabelaPai.getConstraints().add(constraint);
                            } else {
                                //println "nlas";
                            }

                            tabelaReferenciada = tabelas.get((String) dto.rTableName);

                            // atribui o name da contratint
                            constraint.setName(dto.name.toString());
                            //tipo da contraint ( tipo relacionamento ou code contraint )
                            constraint.setTipo(ConstraintType.fromValue(dto.type.toString()));

                            // seta a table (pai) da contraint em questao)
                            constraint.setTable(tabelaPai);
                            // seta a table referenciada tambem da lista de tabelas ( obtem do hash via name)
                            constraint.setReferedTable(tabelaReferenciada);

                        }

                        // obtem da lista de columnPairs da table pai a coluna de name xxxx
                        column = getColumnByName(constraint.getTable(), (String) dto.columnName);
                        colunaReferenciada = getColumnByName(constraint.getReferedTable(), (String) dto.rColumnName);
                        if (column == null) {
                            logger.warn('Column ' + dto.columnName+ ' not found on table: ' + constraint.getTable() );
                            logger.warn('Constraint Name: ' + constraint.name);
                            logger.warn('R Column: ' + colunaReferenciada);
                        }
                        // Adiciona um par de coluna na contraint
                        constraint.getColumnPairs().add(new ColumnPair(column, colunaReferenciada));
                        break

                    case 'U':
                        for (Column columnSearch : tabelaPai.columns) {
                            if (columnSearch.name == dto.columnName) {
                                // adicionando true para a propriedade unique da coluna da tabelaPai
                                columnSearch.unique = true
                                break
                            }
                        }
                        break
                    case 'C':
                        if (dto.searchCondition != null && !dto.searchCondition.toString().contains("IS NOT NULL")) {
                            Column columnSearch = getColumnByName(tabelaPai, dto.columnName?.toString())
                            columnSearch.checkValues = getEnumWithValues(dto.searchCondition?.toString())
                        }
                        break;
                }
            }
        }
    }

    private List<String> getEnumWithValues(String constraintText) {
        List<String> valuesList = new ArrayList<String>()
        def matcher = constraintText =~ /'(\w+)'/
        matcher.each {
            valuesList.add(it[1])
        }
        return valuesList
    }

    private Column getColumnByName(Table table, String columnName) {
        for (def column : table?.columns) {
            if (column.name.equalsIgnoreCase(columnName)) {
                return column
            }
        }
        return null
    }

    private Table getInheritanceTable(Table table) {
        List<Column> pks = table.getPk()

        for (def contraint : table.constraints) {
            List<Column> listaColunasFkQueBatemComPK = new ArrayList<Column>()
            for (def pair : contraint.columnPairs) {
                if (pair.colunaReferenciada == null) {
                    println "contraint sem coluna referenciada ${pair.coluna.table}[${pair.coluna.name}]"
                } else {
                    if (pks.contains(pair.coluna) && pair.colunaReferenciada.table.pk.contains(pair.colunaReferenciada)) {
                        listaColunasFkQueBatemComPK.add(pair.coluna)
                    }
                }
            }
            // se bate esta pk(s) eh constraint que aponta para tabela pai
            if (pks.equals(listaColunasFkQueBatemComPK)) {
                return contraint.referedTable
            }
        }
        return null
    }

}