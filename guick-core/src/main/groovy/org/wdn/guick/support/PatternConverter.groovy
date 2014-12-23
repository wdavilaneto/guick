package org.wdn.guick.support

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.wdn.guick.model.Column

/**
 * Objeto responsavel pelas conversoes de Strings referentes aos patterns
 * da Linguagem Java. Ou seja pattern Converter tem a funcao de transformar as strings
 * em patterns de Nome de Classe, Nome de metodo, nome de instancia de bean e etc...
 *
 */
@Component
class PatternConverter {

    Logger logger = LoggerFactory.getLogger(this.class);

    private typeConverter = new JavaTypeConverter()

    public String getCasedString(String str) {
        return StringUtils.capitalize(str)
    }

    public String getInstanceBeanName(String str) {
        return StringUtils.uncapitalize(str)
    }

    public String getBeanName(String tableName) {
        StringBuffer beanPattern = new StringBuffer("")
        for (String splited : removeFirstPart(tableName.toLowerCase().replaceFirst("^tb_", "")).split("_")) {
            if (splited.equals("tp")) {
                beanPattern.append(getCasedString("Tipo"))
            } else {
                beanPattern.append(getCasedString(splited))
            }
        }
        return beanPattern.toString()
    }

    public String getBeanType(String columnType) {
        def typ = typeConverter.get[columnType.replaceAll(" ", "_")]
        if (typ){
            return typ;
        }
        logger.error("Endefined typ for " + columnType.replaceAll(" ", "_"));
        return "String";
    }

    public String columnToPropertyName(Column column) {
        String columnReturn = removeUnusedParts(column.name)
        String name = columnReturn.replace(column.table.name, "")
        name = getBeanPattern(name.size() > 0 ? name : columnReturn);
        if (column.table.entity != null) {
            String beanName = name.replace(column.table.entity.name, "");
            if (beanName != null && !"".equals(beanName)) {
                name = beanName;
            }
        }
        return name;
    }

    public String columnToPropertyName(String column) {
        String columnReturn = removeUnusedParts(column)
        return getBeanPattern(columnReturn)
    }

    private String removeUnusedParts(String column) {
        String columnReturn = new String(removeFirstPart(column))
        if (columnReturn.startsWith("DT_")) {
            columnReturn = columnReturn.replaceFirst("DT_", "DATA_")
        } else if (columnReturn.startsWith("CD_")) {
            columnReturn = columnReturn.replaceFirst("CD_", "CODIGO_")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.startsWith("TP_")) {
            columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
        } else if (columnReturn.startsWith("SG_")) {
            columnReturn = columnReturn.replaceFirst("SG_", "SIGLA_")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.startsWith("VL_")) {
            columnReturn = columnReturn.replaceFirst("VL_", "VALOR_")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.startsWith("TX_")) {
            columnReturn = columnReturn.replaceFirst("TX_", "TEXTO_")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.startsWith("DS_")) {
            columnReturn = columnReturn.replaceFirst("DS_", "DESCRICAO_")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.startsWith("NR_")) {
            columnReturn = columnReturn.replaceFirst("NR_", "NUMERO_")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.startsWith("NM_")) {
            columnReturn = columnReturn.replaceFirst("NM_", "NOME_")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.startsWith("PGTO_")) {
            columnReturn = columnReturn.replaceFirst("PGTO_", "PAGAMENTO_")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.startsWith("IN_")) {
            columnReturn = columnReturn.replaceFirst("IN_", "INDICADOR_")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.startsWith("TEL_")) {
            columnReturn = columnReturn.replaceFirst("TEL_", "TELEFONE_")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.startsWith("OBS_")) {
            columnReturn = columnReturn.replaceFirst("OBS_", "OBSERVACAO_")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.endsWith("_OBS")) {
            columnReturn = columnReturn.replaceFirst("_OBS", "_OBSERVACAO")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        } else if (columnReturn.endsWith("PGTO")) {
            columnReturn = columnReturn.replaceFirst("_PGTO", "_PAGAMENTO")
            if (columnReturn.contains("_TP_")){
                columnReturn = columnReturn.replaceFirst("TP_", "TIPO_")
            }
        }
        return columnReturn
    }

    public String getBeanPatternWithPrefix(String column) {
        String loweredString = column.toLowerCase()
        StringBuffer buffer
        String beanPattern
        String[] splited = loweredString.split("_")
        switch (splited.length) {
            case 0:
                beanPattern = loweredString
                break
            case 1:
                beanPattern = splited[0].toLowerCase()
                break
            case 2:
                buffer = new StringBuffer(splited[0].toLowerCase())
                // FIXME Campos totalmente fora do padrao ? deveriam abendar o gerador ?
                if (splited[0].length() <= 1) {
                    buffer.append("fixme")
                }
                buffer.append(getCasedString(splited[1]))
                beanPattern = buffer.toString()
                break
            default:
                buffer = new StringBuffer(splited[0].toLowerCase())
                for (int i = 1; i < splited.length; i++) {
                    buffer.append(getCasedString(splited[i]))
                    if (splited[i].length() <= 1) {
                        buffer.append("fixme")
                    }
                }
                beanPattern = buffer.toString()
                break
        }
        return beanPattern
    }

    public String getLastNameColumn(String columnName) {
        String[] values = columnName.split("_")
        return values[values.length - 1]
    }

    private String removeFirstPart(String column) {
        String[] splited = column.split("_")
        if (splited.length > 1 && splited[0].length() == 4) {
            return column.replaceFirst(splited[0] + "_", "")
        }
        return column
    }

    private String getBeanPattern(String column) {

        String beanPattern

        String[] splited = column.split("_")
        if (column.split("_").length == 0) {
            beanPattern = column.toLowerCase()
        } else {
            StringBuffer buffer = new StringBuffer(splited[0].toLowerCase())
            for (int i = 1; i < splited.length; i++) {
                if (splited[i].length() <= 1) {
                    buffer.append("fixme")
                } else {
                    buffer.append(getCasedString(splited[i].toLowerCase()))
                }
            }
            beanPattern = buffer.toString()
        }
        return beanPattern
    }
}
