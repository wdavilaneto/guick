package org.wdn.guick.model

/**
 */
enum ConstraintType {

    // R relacionamento
    // P primary key
    // C code constraint
    Relationship, PrimaryKey, CodeConstraint;

    public String value() {
        return name();
    }

    public static ConstraintType fromValue(String v) {
        if ("P".equals(v)) {
            return PrimaryKey;
        }
        if ("R".equals(v)) {
            return Relationship;
        }
        return CodeConstraint;
    }
}
