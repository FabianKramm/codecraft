package fabian.codecraft.entity;

import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// @Mod.EventBusSubscriber(modid = CodeCraftMod.MODID)
public class EntityEvents {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void entityJoin(final EntityJoinWorldEvent event) {
        if (!event.getWorld().isRemote) {
            ResourceLocation location = EntityList.getKey(event.getEntity());
            if (location != null) {
                LOGGER.info("Entity Joined: key {}", location.toString());
            }
        }
    }
}
