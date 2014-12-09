package org.wdn.guick.model

/**
 */
class DatasourceInfo {

    String driverName = "oracle.jdbc.driver.OracleDriver"
    String user = "sa"
    String password = ""
    String url = "jdbc:oracle:thin:@localhost:1521:xe"
    String dialect = "org.hibernate.dialect.Oracle10gDialect"
}
