package org.wdn.guick.support

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.wdn.guick.model.Column

/**
 * Created by IntelliJ IDEA.
 * User: y1z5
 * Date: 02/03/12
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */
class JavaTypeConverter {

    Logger logger = LoggerFactory.getLogger(this.class);

    def get = ["DATE"                             : "Date",
               "TIMESTAMP"                        : "Date",
               "TIMESTAMP(6)"                     : "Date",
               "TIMESTAMP(6) WITH LOCAL TIME ZONE": "Date",
               "CHAR"                             : "String",
               "NUMBER"                           : "Long",
               "LONG"                             : "Long",
               "INTEGER"                          : "Long",
               "VARCHAR2"                         : "String",
               "TEXT"                             : "String",
               "CHARACTER"                        : "String",
               "CHARACTER VARYING"                : "String",
               "NVARCHAR2"                        : "String",
               "VARCHAR"                          : "String",
               "CLOB"                             : "String",
               "BLOB"                             : "byte[]",
               "RAW"                              : "Object",
               "LONG RAW"                         : "Long",
               "NCHAR"                            : "String",
               "XMLTYPE"                          : "String",
               "SDO GEOMETRY"                     : "String",
               "INTERVAL YEAR(2) TO MONTH"        : "String",
               "BIGINT"                           : "Long",
               "OID"                              : "Long",
               "BOOLEAN"                          : "Boolean",
               "DOUBLE"                           : "Double",
               "DOUBLE PRECISION"                 : "Double",
               "BYTEA"                            : "Integer",
               "TIMESTAMP WITHOUT TIME ZONE"      : "Date",
               "SMALLINT"                         : "Integer",
                '"CHAR"':"String",

    ];

    public String getType(Column col) {
        if (get.containsKey(col.type.toUpperCase())) {
            if (get[col.type.toUpperCase()].equals("Long") && !col.isKey() ) {
                if ((col.scale != null && col.scale != 0) && (col.precision != null && col.scale !=0 )) {
                    return "BigDecimal"
                }
            }
            return get[col.type.toUpperCase()]
        }
        logger.error("Undefined type for " + col.type);
        return "String"
    }
}
