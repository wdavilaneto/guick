package org.wdn.guick.support

import org.apache.commons.lang.StringUtils
import org.wdn.guick.model.Entity

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 */
class TemplateUtil {

    public String getCapitalized(String codeClassName) {
        return StringUtils.capitalize(codeClassName);
    }

    public String getUncaptalized(String codeClassName) {
        return StringUtils.uncapitalize(codeClassName);
    }

    public String separaNomes(String codeClassName) {
        if (codeClassName == null) {
            return null;
        }
        if (codeClassName.trim().isEmpty()) {
            return "";
        }

        Pattern padrao = Pattern.compile("[A-Z]");
        Matcher letras = padrao.matcher(codeClassName);

        while (letras.find()) {
            codeClassName = codeClassName.replaceFirst(letras.group(), " " + letras.group().toLowerCase())
        }

        if (codeClassName.charAt(0) == ' ') {
            codeClassName = codeClassName.substring(1);
        }

        String nomeCapit = codeClassName.substring(0,1).toUpperCase();

        for (int i=1; i<codeClassName.size(); i++) {
            if (codeClassName.charAt(i-1) == ' ') {
                nomeCapit += codeClassName.charAt(i).toString().toUpperCase();
            }
            else {
                nomeCapit += codeClassName.charAt(i).toString();
            }
        }
        codeClassName = nomeCapit;

        StringTokenizer st = new StringTokenizer(codeClassName, " ");
        if (st.countTokens() > 1) {
            String primeiraPalavra = st.nextElement()
//            if (primeiraPalavra.equals("Data")) {
//                codeClassName = codeClassName.replaceFirst("Data ", "")
//            } else
            if (primeiraPalavra.equals("Codigo")) {
                codeClassName = codeClassName.replaceFirst("Codigo ", "")
            } else if (primeiraPalavra.equals("Texto")) {
                codeClassName = codeClassName.replaceFirst("Texto ", "")
            } else if (primeiraPalavra.equals("Descricao")) {
                codeClassName = codeClassName.replaceFirst("Descricao ", "")
            } else if (primeiraPalavra.equals("Numero")) {
                codeClassName = codeClassName.replaceFirst("Numero ", "")
            } else if (primeiraPalavra.equals("Nome")) {
                codeClassName = codeClassName.replaceFirst("Nome ", "")
            } else if (primeiraPalavra.equals("Indicador")) {
                codeClassName = codeClassName.replaceFirst("Indicador ", "")
            }
        }

        return codeClassName;
    }

    public String tableToMnemonic (String codeClassName) {
        if (codeClassName == null) {
            return null;
        }
        if (codeClassName.trim().isEmpty()) {
            return "";
        }

        StringTokenizer st = new StringTokenizer(codeClassName, "_");

        String sigla = new String();

        switch (st.countTokens()) {
            case 1:
                sigla = codeClassName.substring(0,4);
                break;
            case 2:
                sigla = st.nextToken().substring(0,2) +
                        st.nextToken().substring(0,2);
                break;
            case 3:
                sigla = st.nextToken().substring(0,1) +
                        st.nextToken().substring(0,1) +
                        st.nextToken().substring(0,2);
                break;
            default:
                sigla = st.nextToken().substring(0,1) +
                        st.nextToken().substring(0,1) +
                        st.nextToken().substring(0,1) +
                        st.nextToken().substring(0,1);
                break;
        }

        return sigla.toUpperCase();
    }

    public String getLabel(Entity entity){

        def returnName = "id"
        for (def prop : entity.properties) {
            if (prop.name.equals("nome")) {
                return prop.name
            }
        }
        for (def prop : entity.properties) {
            if ( prop.name.startsWith("nome") ) {
                return prop.name;
            }
            if ( prop.name.startsWith("texto")){
                returnName = prop.name;
            }
        }
        if ("id".equals(returnName) && entity.parent != null) {
            returnName = getLabel(entity.parent)
        }
        if ("id".equals(returnName) && entity.embeddedId != null) {
            returnName = "id." + getLabel(entity.embeddedId)
        }
        return returnName

    }

    public boolean hasLabel (Entity entity , String label){

        for (def prop : entity.properties) {
            if (prop.name.equals("nome")) {
                return true
            }
        }
        if ( entity.parent != null) {
            return hasLabel(entity.parent , label)
        }
        return false

    }



}
