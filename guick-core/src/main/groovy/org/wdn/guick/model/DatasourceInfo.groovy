package org.wdn.guick.model

/**
 */
class DatasourceInfo {

    String driverName = "oracle.jdbc.driver.OracleDriver"
    String user = "sys"
    String password = "Oradoc_db1"
    String url = "jdbc:oracle:thin:@localhost:32768:ORCLCDB"
    String dialect = "org.hibernate.dialect.Oracle10gDialect"
}
