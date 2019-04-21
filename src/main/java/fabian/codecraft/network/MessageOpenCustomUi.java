package fabian.codecraft.network;

import fabian.codecraft.CodeCraftMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nullable;

public class MessageOpenCustomUi implements IMessage {
    public MessageOpenCustomUi() {}


    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf The buffer
     */
    @Override
    public void fromBytes(final ByteBuf buf) {

    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf The buffer
     */
    @Override
    public void toBytes(final ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageOpenCustomUi, IMessage> {

        /**
         * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
         * is needed.
         *
         * @param message The message
         * @param ctx     The message context
         * @return an optional return message
         */
        @Nullable
        @Override
        public IMessage onMessage(final MessageOpenCustomUi message, final MessageContext ctx) {
            CodeCraftMod.proxy.getThreadListener(ctx).addScheduledTask(() -> CodeCraftMod.proxy.displayCustomUi());
            return null;
        }
    }
}
