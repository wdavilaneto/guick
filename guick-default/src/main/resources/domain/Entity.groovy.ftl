package ${project.group}.${project.name}.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
class ${entity.name} implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id

#foreach( ${property} in ${entity.properties})
    ${property.type} ${property.name}
#end

}
