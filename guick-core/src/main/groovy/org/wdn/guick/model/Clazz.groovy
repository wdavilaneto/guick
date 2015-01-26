package org.wdn.guick.model

import org.apache.commons.lang.StringUtils
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder


/**
 *
 */
class Clazz implements Serializable {

    private static final long serialVersionUID = 4832846464666782423L;
    private static final PACKAGE_SEPARATOR = "."

    private String codePackage;
    String name
    String inheritedClass

    String extension = "java" // defaultValue
    String type = null

    List<String> interfaces = new ArrayList<String>()
    List<String> imports = new ArrayList<String>()

    List<Clazz> properties = new ArrayList<Clazz>(0);
    List<CollectionProperty> collections;

    ClazzStereotype stereotype = ClazzStereotype.Implementation;

    // Bi-directional relations ..
    Project project

    public String getTitle(){
        // TODO: remove to business rule processor
        def arry = StringUtils.splitByCharacterTypeCamelCase(name).toList();
        if ( (arry[0].equals("texto") || arry[0].equals("sigla"))&& arry.size () > 1 ){
            arry = arry[1..(arry.size ()-1)];
        }
        String word = arry.join(" ").toLowerCase();
        if (word.endsWith("collection")){
            word = word.replaceFirst("collection" , "")
        }
        if (word.equalsIgnoreCase("CPF") || word.equalsIgnoreCase("CEP") || word.equalsIgnoreCase("CNPJ")){
            return word.toUpperCase();
        } else {
            for(def map in [
                    [chave:"descricao",acentuado:"descrição"],
                    [chave:"Descricao",acentuado: "Descrição"],
                    [chave:"responsavel",acentuado: "responsável"],
                    [chave:"Responsavel",acentuado: "Responsável"],
                    [chave:"instituicao",acentuado: "instituição"],
                    [chave:"Instituicao",acentuado: "Instituição"],
                    [chave:"inicio",acentuado: "início"],
                    [chave:"votacao",acentuado: "votação"],
            ]){
                if (word.contains(map.chave)){
                    word = word.replaceFirst(map.chave,map.acentuado);
                }
            }
        }
        return StringUtils.capitalize(word);
    }

    public String getCapitalizedName(){
        return  StringUtils.capitalize(name);
    }

    public getPackage() {
        if (codePackage == null) {
            return project.group + PACKAGE_SEPARATOR + project.name
        }
        return codePackage
    }

    public setPackage(String codePackage) {
        this.codePackage = codePackage
    }

    public getType() {
        if (type == null) {
            return name
        }
        return type
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(name).
                append(getPackage()).
                toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Clazz rhs = (Clazz) obj;
        return new EqualsBuilder()
                .append(name, rhs.name)
                .append(getPackage(), rhs.getPackage())
                .isEquals();
    }

    @Override
    public String toString(){
        return name
    }

}

