package fabian.codecraft;

import fabian.codecraft.ai.CustomAi;
import fabian.codecraft.util.AiUtil;
import fabian.codecraft.util.PathUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IEntityLiving;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scala.tools.nsc.doc.base.comment.Code;

import java.util.Iterator;

public class ScriptUtils {
    private static final Logger LOGGER = LogManager.getLogger();

    public enum AiType {
        CUSTOM_AI("CustomAi");

        private final String text;

        /**
         * @param text
         */
        AiType(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }

        public static AiType fromString(String text) {
            for (AiType b : AiType.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b;
                }
            }

            return null;
        }
    }

    public static void AddAiTask(ICustomNpc entity, String taskType) {
        AiType type = AiType.fromString(taskType);
        EntityLiving entityLiving = entity.getMCEntity();

        switch(type){
            case CUSTOM_AI:
                if (!AiUtil.hasAiTask(entityLiving, CustomAi.class)) {
                    LOGGER.info("Add Task!");
                    entityLiving.tasks.addTask(1, new CustomAi(entityLiving));
                }

                break;
            default:
                break;
        }
    }

    public static void navigateOrTpTo(IEntityLiving entityLiving, double x, double y, double z, double speed) {
        Path path = entityLiving.getMCEntity().getNavigator().getPathToXYZ(x, y, z);

        if(path != null && path.getFinalPathPoint() != null) {
            PathPoint pathPoint = path.getFinalPathPoint();

            if(PathUtil.manhatten((int)x, (int)y, (int)z, pathPoint.x, pathPoint.y, pathPoint.z) < 2) {
                entityLiving.getMCEntity().getNavigator().clearPath();
                entityLiving.getMCEntity().getNavigator().setPath(path, speed);
                return;
            }
        }

        entityLiving.getMCEntity().setPosition(x, y, z);
        entityLiving.getMCEntity().getNavigator().clearPath();
    }
}
