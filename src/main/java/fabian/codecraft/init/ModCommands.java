package fabian.codecraft.init;

import fabian.codecraft.command.CommandCodeCraft;
import fabian.codecraft.command.CommandEval;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Registers this mod's commands.
 *
 * @author FabianKramm
 */
public class ModCommands {

    /**
     * Register the commands.
     *
     * @param event The server starting event
     */
    public static void registerCommands(final FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandCodeCraft());
    }

    /**
     * Register the sub-commands of the {@code /testmod3} command.
     *
     * @param commandCodeCraft The /codecraft command
     */
    public static void registerSubCommands(final CommandCodeCraft commandCodeCraft) {
        commandCodeCraft.addSubcommand(new CommandEval());
    }
}