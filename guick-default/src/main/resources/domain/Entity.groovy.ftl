package ${project.group}.${project.name}.domain

import javax.persistence.Entity
import javax.persistence.Id


@Entity
class ${entity.name} implements Serializable {

    @Id
    Long id

#foreach( ${property} in ${entity.properties})
    ${property.type} ${property.name}
#end

}
