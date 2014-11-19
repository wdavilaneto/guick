package org.wdn.guick.support

/**
 * Created by IntelliJ IDEA.
 * User: y1z5
 * Date: 02/03/12
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */
class JavaTypeConverter {

    def get = ["DATE": "Date",
            "TIMESTAMP": "Date",
            "TIMESTAMP(6)": "Date",
            "CHAR": "String",
            "NUMBER": "Long",
            "LONG": "Long",
            "INTEGER": "Long",
            "VARCHAR2": "String",
            "NVARCHAR2": "String",
            "VARCHAR": "String",
            "CLOB": "String",
            "BLOB": "byte[]",
            "RAW": "Object",
            "LONG_RAW": "Long",
            "NCHAR": "String"]
}
