package org.wdn.guick.support

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.wdn.guick.model.Project
import org.wdn.guick.model.Entity
import org.springframework.stereotype.Component

/**
 * Created by IntelliJ IDEA.
 * User: wdavilaneto
 * Date: 02/05/12
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
@Component
class SequenceNameBRP implements IBusinessRulesProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.class)

    @Override
    Project doProcess(Project project) {
        for (Entity entity : project.getEntities()) {
            if (entity.table != null && entity.table.sequenceName == null) {
                try {
                    //entity.table.sequenceName = "SQ_" + entity.table.getPk()[0].name
                    if ( entity.table.getPk().size() == 1 ){
                        // TODO validate this formation
                        if (entity.table.getPk()[0].type.toUpperCase().startsWith("CHARACTER")) {
                            entity.table.getPk()[0].generated = true;
                            entity.table.getPk()[0].useUUID = true;
                            entity.table.sequenceName = "uuid"
                        } else {
                            entity.table.sequenceName = entity.table.owner + "_SQ_" + entity.table.entity.table.getPk()[0].name.split("_")[0] + "_DK";
                        }
                    } else {
                        // TODO validate this formation
                        entity.table.sequenceName = entity.table.owner + "_SQ_" + entity.table.entity.table.columns[0].name.split("_")[0]+ "_DK";
                    }
                } catch (e) {
                    logger.println("Entity without Table or no PK ..." + entity.table.name)
                }
            }
        }
        return project;
    }
}
