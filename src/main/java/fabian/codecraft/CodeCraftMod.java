package fabian.codecraft;

import fabian.codecraft.api.Api;
import fabian.codecraft.controller.ScriptController;
import fabian.codecraft.init.ModCommands;
import fabian.codecraft.init.ModMessages;
import fabian.codecraft.proxy.IProxy;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.UUID;

@Mod(modid = CodeCraftMod.MODID, name = CodeCraftMod.NAME, version = CodeCraftMod.VERSION)
public class CodeCraftMod
{
    public static final String MODID = "codecraft";
    public static final String NAME = "Code Craft Mod";
    public static final String VERSION = "1.0";
    public static MinecraftServer Server;

    private static final Logger LOGGER = LogManager.getLogger();

    public static SimpleNetworkWrapper network;

    @SidedProxy(clientSide = "fabian.codecraft.proxy.CombinedClientProxy", serverSide = "fabian.codecraft.proxy.DedicatedServerProxy")
    public static IProxy proxy;

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        FMLLog.bigWarning("Random UUID: {}", UUID.randomUUID().toString());

        network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

        ModMessages.registerMessages();

        new Api();
        new ScriptController();

        proxy.preInit();
    }

    @EventHandler
    public void init(final FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @EventHandler
    public void serverStarting(final FMLServerStartingEvent event) {
        ModCommands.registerCommands(event);
    }

    @EventHandler
    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
        Server = event.getServer();
    }
}
