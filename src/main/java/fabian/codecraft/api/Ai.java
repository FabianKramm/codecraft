package fabian.codecraft.api;

import fabian.codecraft.ai.CustomAi;
import fabian.codecraft.util.AiUtil;
import net.minecraft.entity.EntityLiving;
import noppes.npcs.api.entity.ICustomNpc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ai {
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

    public void AddAiTask(ICustomNpc entity, String taskType) {
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
}
