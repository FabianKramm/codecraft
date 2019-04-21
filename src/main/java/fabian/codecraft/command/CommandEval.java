package fabian.codecraft.command;

import fabian.codecraft.controller.ScriptContainer;
import fabian.codecraft.controller.ScriptController;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandEval extends CommandBase {
    /**
     * Return the required permission level for this command.
     */
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    /**
     * Gets the name of the command.
     */
    @Override
    public String getName() {
        return "eval";
    }

    /**
     * Gets the usage string for the command.
     *
     * @param sender The command sender that executed the command
     */
    @Override
    public String getUsage(final ICommandSender sender) {
        return "commands.codecraft.eval.usage";
    }

    /**
     * Callback for when the command is executed
     *
     * @param server The Minecraft server instance
     * @param sender The source of the command invocation
     * @param args   The arguments that were passed
     */
    @Override
    public void execute(final MinecraftServer server, final ICommandSender sender, final String[] args) throws CommandException {
        if (ScriptController.Instance != null) {
            String out = ScriptController.Instance.eval(String.join(" ", args), sender.getCommandSenderEntity());
            sender.sendMessage(new TextComponentTranslation(out));
        }
    }
}
