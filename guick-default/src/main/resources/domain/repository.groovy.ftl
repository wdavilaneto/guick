package ${project.group}.${project.name}.persistence;

import com.wdavilaneto.wdavilaneto.domain.${entity.name};
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *  CRUD Genreated Repository for entity${entity.name}
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface ${entity.name}Repository extends PagingAndSortingRepository<${entity.name}, Long > {
}