package org.wdn.guick.model

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import org.wdn.guick.util.StringUtil

class Entity extends Clazz {

    Table table = null
    Entity parent = null;
    List<Entity> childrens = new ArrayList<Entity>(0);

    Entity embeddedId = null;
    Entity embeddedFor = null; // Bidirectionla Association

    RelationshipProperty id;

    List<RelationshipProperty> properties = new ArrayList<RelationshipProperty>(0);
    List<ComplexProperty> complexProperties = new ArrayList<ComplexProperty>(0);

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

    public List<RelationshipProperty> getAllObrigatoryProperties() {
        List<RelationshipProperty> returnList = new ArrayList<RelationshipProperty>()
        for (RelationshipProperty property : properties) {
            if (!property.column.nullable) {
                returnList.add(property);
            }
        }
        return returnList;
    }

    public List<RelationshipProperty> getAllNumericProperties() {
        List<RelationshipProperty> returnList = new ArrayList<RelationshipProperty>()
        for (RelationshipProperty property : properties) {
            if (property.type == 'Long' || property.type == 'Integer' || property.type == 'BigDecimal'  ) {
                returnList.add(property);
            }
        }
        return returnList;
    }

    public List<RelationshipProperty> getAllImageProperties() {
        List<RelationshipProperty> returnList = new ArrayList<RelationshipProperty>()
        for (RelationshipProperty property : properties) {
            if (property.type == 'byte[]' && (property.name.toLowerCase().contains("photo")
                    || property.name.toLowerCase().contains("foto")
                    || property.name.toLowerCase().contains("image")
                    || property.name.toLowerCase().contains("imagem")
                    || property.name.toLowerCase().contains("icon")
                    || property.name.toLowerCase().contains("portrait")
                    || property.name.toLowerCase().contains("pitcture"))
            ) {
                returnList.add(property);
            }
        }
        return returnList;
    }

    private _mostDescritiveProperties = null;

    public List<RelationshipProperty> getMostDescritiveProperties() {
        if (_mostDescritiveProperties == null) {
            _mostDescritiveProperties = new ArrayList<RelationshipProperty>()
            for (RelationshipProperty property : properties) {
                if (property.type == 'String' && (property.name.startsWith("nome") || property.name.startsWith("name"))  ) {
                    _mostDescritiveProperties.add(property);
                }
            }
            for (RelationshipProperty property : parent?.properties) {
                if (property.type == 'String' && (property.name.startsWith("nome") || property.name.startsWith("name"))  ) {
                    _mostDescritiveProperties.add(property);
                }
            }
            for (RelationshipProperty property : properties) {
                if (property.type == 'String' && (!property.name.startsWith("nome") && !property.name.startsWith("name"))) {
                    _mostDescritiveProperties.add(property);
                }
            }
            for (RelationshipProperty property : parent?.properties) {
                if (property.type == 'String' && (!property.name.startsWith("nome") && !property.name.startsWith("name"))) {
                    _mostDescritiveProperties.add(property);
                }
            }
//            if (_mostDescritiveProperties.size() == 0){
//                _mostDescritiveProperties.add(id);
//            }
            // Todo order for significance...
        }
        return _mostDescritiveProperties;
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

    public List<ComplexProperty> getDistinctedAllComplexProperties() {
        Map<String, String> map = new HashMap<String, String>();
        List<ComplexProperty> returnList = new ArrayList<ComplexProperty>()
        for (ComplexProperty property : complexProperties) {
            if (!map.containsKey(property.referedEntity.name)) {
                map.put(property.referedEntity.name, property.name);
                returnList.add(property);
            }
        }
        for (ComplexProperty property : parent?.complexProperties) {
            if (!map.containsKey(property.referedEntity.name)) {
                map.put(property.referedEntity.name, property.name);
                returnList.add(property);
            }
        }
        return returnList;
    }

    public List<RelationshipProperty> getDistinctedProperties() {
        Map<String, String> map = new HashMap<String, String>();
        List<RelationshipProperty> returnList = new ArrayList<RelationshipProperty>()
        for (RelationshipProperty property : properties) {
            if (!map.containsKey(property.name)) {
                map.put(property.name, property.name);
                returnList.add(property);
            }
        }
        for (RelationshipProperty property : parent?.properties) {
            if (!map.containsKey(property.name)) {
                map.put(property.name, property.name);
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

    public boolean hasHibernateIssue() {
        if (parent?.id instanceof Entity) {
            Entity parentId = (Entity) parent.id
            return parentId.isEmbeddable();
        }
        return false;
    }

    private List<RelationshipProperty> _allDeadLineProperties = null;
    public List<RelationshipProperty> getAllDeadlineProperties() {
        if (_allDeadLineProperties == null) {
            _allDeadLineProperties = new ArrayList<RelationshipProperty>()
            for (RelationshipProperty property : properties) {
                if (property.looksDeadline()) {
                    _allDeadLineProperties.add(property);
                }
            }
        }
        return _allDeadLineProperties;
    }
    public boolean hasDeadline() {
        return getAllDeadlineProperties().size() > 0;
    }

    private List<RelationshipProperty> _allEndDateProperties = null;
    public List<RelationshipProperty> getAllEndDateProperties() {
        if (_allEndDateProperties == null) {
            _allEndDateProperties = new ArrayList<RelationshipProperty>()
            for (RelationshipProperty property : properties) {
                if (property.looksLikeEndDate()) {
                    _allEndDateProperties.add(property);
                }
            }
        }
        return _allEndDateProperties;
    }
    public boolean hasEndDate() {
        return getAllEndDateProperties().size() > 0;
    }

    private List<RelationshipProperty> _allBeginDateProperties = null;
    public List<RelationshipProperty> getAllBeginDateProperties() {
        if (_allBeginDateProperties == null) {
            _allBeginDateProperties = new ArrayList<RelationshipProperty>()
            for (RelationshipProperty property : properties) {
                if (property.looksLikeBeginDate()) {
                    _allBeginDateProperties.add(property);
                }
            }
        }
        return _allBeginDateProperties;
    }
    public boolean hasBeginDate() {
        return getAllEndDateProperties().size() > 0;
    }


    private Boolean _looksLikeEnum = null;

    public boolean looksLikeEnum() {
        if (_looksLikeEnum == null) {
            _looksLikeEnum = ( properties.size() == 1 && getManyToOneProperties().size() == 0 && parent == null && getEmbeddedId() == null ) || (_looksLikeEnum = properties.size() == 2 && name.startsWith("Tipo"));
        }
        return _looksLikeEnum;
    }

    private Boolean _looksLikeDomain = null;

    public boolean looksLikeDomain() {
        if (_looksLikeDomain == null) {
            (_looksLikeDomain = properties.size() < 3 && !looksLikeEnum() && parent == null || (!looksLikeEnum() && (name.toLowerCase().startsWith("tipo") || name.toLowerCase().startsWith("type") || name.toLowerCase().endsWith("type") ) ) );
        }
        return _looksLikeDomain;
    }
    private Boolean _looksLikeMainEntity = null;

    public boolean looksLikeMainEntity() {
        if (_looksLikeMainEntity == null) {
            _looksLikeMainEntity = !looksLikeEnum() && !looksLikeDomain();
        }
        return _looksLikeMainEntity
    }

    public RelationshipProperty getActiveProperty() {
        def activekeywords = ["indicadorAtivo", "active", "ativo"];
        for (RelationshipProperty prop : properties) {
            for (def word : activekeywords ) {
                if (word.equals(prop.name) ) {
                    return prop;
                }
            }
        }
        for (EnumClass prop : enums) {
            for (def word : activekeywords ) {
                if (word.equals(prop.getSimpleProperty().name)) {
                    return prop.getSimpleProperty();
                }
            }
        }
        if (hasEndDate()){
            return getAllEndDateProperties()[0];
        }
        return null;
    }




    public String getPackage() {
        return project.group + "." + project.acronym + ".domain";
    }

    public boolean containsPropertyName(String parameter) {
        for (Clazz prop : properties) {
            if (prop.name.equals(parameter)) {
                return true
            }
        }
        for (def prop : complexProperties) {
            if (prop.name.equals(parameter) ) { // && !prop.isOneToMany() && !prop.isManyToMany()
                return true
            }
        }
        return false
    }

    public Clazz getPropertyWithName(String name){
        for (RelationshipProperty prop : properties) {
            if (prop.name.equals(name)) {
                return prop
            }
        }
        for (ComplexProperty prop : complexProperties) {
            if (prop.name.equals(name)) {
                return prop
            }
        }
        return null;
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
