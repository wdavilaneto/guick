package org.wdn.guick.support

import org.springframework.stereotype.Component
import org.wdn.guick.model.*

/**
 * Created with IntelliJ IDEA.
 * User: wdavilaneto
 * Date: 15/05/12
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 */
@Component
class ValidationsBRP implements IBusinessRulesProcessor {

    @Override
    Project doProcess(Project project) {

        for (Entity entity : project.entities) {

            processProperties(entity.properties);

            processComplexProperties(entity.complexProperties)

        }
        return project;
    }

    private void processProperties(List<RelationshipProperty> properties) {
        Validation validation;
        for (def simpleProperty : properties) {
            if (!simpleProperty.column.nullable) {

                validation = new Validation()
                validation.setName("NotNull")
                simpleProperty.validations.add(validation);

//                if (simpleProperty.getType().equals("String")) {
//                    validation = new Validation()
//                    validation.setName("NotBlank")
//                    simpleProperty.validations.add(validation);
//                }
            }

            if (!simpleProperty.column.length != null && simpleProperty.getType().equals("String")) {
                validation = new Validation()
                validation.setName("Size")
                validation.parameters.put("max", simpleProperty.column.length)
                simpleProperty.validations.add(validation);
            }

//            if ("cpf".equalsIgnoreCase(simpleProperty.name) || "numerocpf".equalsIgnoreCase(simpleProperty.name)) {
//                validation = new Validation()
//                validation.setName("CPF")
//                simpleProperty.validations.add(validation);
//            }
        }
    }

    private void processComplexProperties(List<ComplexProperty> complexProperties) {
        Validation validation;
        for (def complexProperty : complexProperties) {
            if (isNotNullConstraintColumn(complexProperty.getConstraint())) {
                validation = new Validation()
                validation.setName("NotNull")
                complexProperty.validations.add(validation);
            }
        }
    }

    private boolean isNotNullConstraintColumn(Constraint constraint) {
        return (constraint != null && !constraint.singleColumnPair?.coluna?.nullable)
    }

}
