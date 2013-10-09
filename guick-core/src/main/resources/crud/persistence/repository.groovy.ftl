package ${project.group}.${project.name}.persistence;

import ${project.group}.${project.name}.domain.${entity.name};
import ${project.group}.${project.name}.persistence.support.ICoreRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *  CRUD Genreated Repository for entity${entity.name}
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface ${entity.name}Repository extends PagingAndSortingRepository<${entity.name}, Long >, ICoreRepository<${entity.name}, Long > {
}