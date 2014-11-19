package org.wdn.guick.support

import org.wdn.guick.model.Column

/**
 * Created by IntelliJ IDEA.
 * User: y1z5
 * Date: 15/02/12
 * Time: 18:58
 * To change this template use File | Settings | File Templates.
 */
class PatternConverterFacade {

    private static PatternConverter patternConverter

    public static String getCasedString(String str) {
        return getPatternConverter().getCasedString(str);
    }

    public static String getInstanceBeanName(String str) {
        return getPatternConverter().getInstanceBeanName(str)
    }

    public static String getBeanName(String tableName) {
        return getPatternConverter().getBeanName(tableName)
    }

    public static String getBeanType(String columnType) {
        return getPatternConverter().getBeanType(columnType)
    }

    public static String columnToPropertyName(Column column) {
        return getPatternConverter().columnToPropertyName(column)
    }

    public static String columnToPropertyName(String column) {
        return getPatternConverter().columnToPropertyName(column)
    }

    public static String getBeanPatternWithPrefix(String column) {
        return getPatternConverter().getBeanPatternWithPrefix(column)
    }

    public static String getLastNameColumn(String column) {
        return getPatternConverter().getLastNameColumn(column)
    }

    protected static PatternConverter getPatternConverter() {
        if (patternConverter == null) {
            patternConverter = new PatternConverter()
        }
        return patternConverter;
    }

}
