package org.wdn.guick.model

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import org.wdn.guick.support.PatternConverter
import org.wdn.guick.util.StringUtil

class Entity extends Clazz {

    Table table = null
    Entity parent = null;
    List<Entity> childrens = new ArrayList<Entity>(0);

    Entity embeddedId = null;
    Entity embeddedFor = null; // Bidirectionla Association

    RelationshipProperty id;

    List<RelationshipProperty> properties = new ArrayList<RelationshipProperty>(0)
    List<ComplexProperty> complexProperties = new ArrayList<ComplexProperty>(0)

    List<EnumClass> enums = new ArrayList<EnumClass>()

    private StringUtil stringUtil = new StringUtil();

    public boolean isEmbeddable() {
        return embeddedFor != null
    }

    public Table getTable() {
        if (isEmbeddable()) {
            return embeddedFor.table
        }
        return table;
    }

    public Clazz getId() {
        if (embeddedId != null) {
            return embeddedId;
        }
        if (parent != null) {
            return parent.id
        }
        return id
    }

    public List<RelationshipProperty> getAllObrigatoryProperties(){
        List<RelationshipProperty> returnList = new ArrayList<RelationshipProperty>()
        for (RelationshipProperty property : properties) {
            if (!property.column.nullable) {
                returnList.add(property);
            }
        }
        return returnList;
    }

    public List<ComplexProperty> getOneToManyProperties() {
        List<ComplexProperty> returnList = new ArrayList<ComplexProperty>(complexProperties.size())
        for (ComplexProperty property : complexProperties) {
            if (property.isOneToMany()) {
                returnList.add(property);
            }
        }
        return returnList;
    }

    public List<ComplexProperty> getManyToOneProperties() {
        List<ComplexProperty> returnList = new ArrayList<ComplexProperty>(complexProperties.size())
        for (ComplexProperty property : complexProperties) {
            if (property.isManyToOne()) {
                returnList.add(property);
            }
        }
        return returnList;
    }

    public List<ComplexProperty> getManyToManyProperties() {
        List<ComplexProperty> returnList = new ArrayList<ComplexProperty>(complexProperties.size())
        for (ComplexProperty property : complexProperties) {
            if (property.isManyToMany()) {
                returnList.add(property);
            }
        }
        return returnList;
    }

    private Boolean _looksLikeEnum = null;
    public boolean looksLikeEnum(){
        if (_looksLikeEnum == null) {
            (_looksLikeEnum = properties.size() == 1 && getManyToOneProperties().size() == 0) || (_looksLikeEnum = properties.size() ==2 && name.startsWith("Tipo") );
        }
        return _looksLikeEnum;
    }

    private Boolean _looksLikeDomain = null;
    public boolean looksLikeDomain(){
        if (_looksLikeDomain == null) {
            (_looksLikeDomain = properties.size() < 3 && !looksLikeEnum() );
        }
        return _looksLikeDomain;
    }

    public String getPackage() {
        return project.group + "." + project.acronym + ".domain";
    }

    public boolean containsPropertyName(def name) {
        for (Clazz prop : properties) {
            if (prop.name.equals(name)) {
                return true
            }
        }
        for (Clazz prop : complexProperties) {
            if (prop.name.equals(name)) {
                return true
            }
        }
        return false
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                appendSuper(super.hashCode()).
                append(table).
                toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Entity rhs = (Entity) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(table, rhs.table)
                .isEquals();
    }

    public String getBeanName() {
        return stringUtil.uncapitalize(name);
    }

}
