package fabian.codecraft.init;

import fabian.codecraft.CodeCraftMod;
import fabian.codecraft.item.ItemTest;
import fabian.codecraft.util.RegistryUtils;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

import static fabian.codecraft.util.InjectionUtils.Null;
import static fabian.codecraft.util.RegistryUtils.setItemName;

@SuppressWarnings("WeakerAccess")
@ObjectHolder(CodeCraftMod.MODID)
public class ModItems {
    public static final ItemTest TEST = Null();

    @Mod.EventBusSubscriber(modid = CodeCraftMod.MODID)
    public static class RegistrationHandler {
        public static final Set<Item> ITEMS = new HashSet<>();

        /**
         * Register this mod's {@link Item}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final Item[] items = {
                    setItemName(new ItemTest(), "test"),
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final Item item : items) {
                RegistryUtils.setDefaultCreativeTab(item);
                registry.register(item);
                ITEMS.add(item);
            }
        }
    }
}