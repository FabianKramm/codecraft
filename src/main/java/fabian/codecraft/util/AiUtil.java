package fabian.codecraft.util;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAITasks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class AiUtil {
    private static final Logger LOGGER = LogManager.getLogger();

    public static boolean hasAiTask(EntityLiving entityLiving, Class c) {
        Iterator<EntityAITasks.EntityAITaskEntry> i = entityLiving.tasks.taskEntries.iterator();

        while(i.hasNext()) {
            EntityAITasks.EntityAITaskEntry next = i.next();

            if (next.action.getClass() == c) {
                return true;
            }
        }

        return false;
    }
}
