package org.wdn.guick.support

import org.wdn.guick.model.Column
import org.wdn.guick.model.Table

/**
 * Created by IntelliJ IDEA.
 * User: wdavilaneto
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

    public static String getBeanName(Table table) {
        return getPatternConverter().getBeanName(table)
    }

    public static String getBeanType(Column column) {
        return getPatternConverter().getBeanType(column)
    }

    public static String columnToPropertyName(Column column) {
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
