package org.wdn.guick.util

import org.apache.commons.lang.StringUtils
import org.springframework.stereotype.Component

@Component
class StringUtil {

    public String capitalize(String str) {
        return StringUtils.capitalize(str)
    }

    public String uncapitalize(String str) {
        return StringUtils.uncapitalize(str)
    }

    public String abbreviate(String str){
        return StringUtils.abbreviate(str)
    }

}
