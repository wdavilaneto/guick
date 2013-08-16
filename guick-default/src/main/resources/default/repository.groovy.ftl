package ${project.group}.${project.name}.persistence.Repository;

import org.springframework.data.repository.Repository;

public interface CrudRepository extends Repository<RssSource, Long > {
}