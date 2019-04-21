package fabian.codecraft.init;

import fabian.codecraft.CodeCraftMod;
import fabian.codecraft.network.MessageOpenCustomUi;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.relauncher.Side;

public class ModMessages {
    // Start the IDs at 1 so any unregistered messages (ID 0) throw a more obvious exception when received
    private static int messageID = 1;

    public static void registerMessages() {
        registerMessage(MessageOpenCustomUi.Handler.class, MessageOpenCustomUi.class, Side.CLIENT);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(final Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, final Class<REQ> requestMessageType, final Side receivingSide) {
        CodeCraftMod.network.registerMessage(messageHandler, requestMessageType, messageID++, receivingSide);
    }
}