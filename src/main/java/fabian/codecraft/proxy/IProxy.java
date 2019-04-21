package fabian.codecraft.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nullable;

public interface IProxy {
    void preInit();

    void init();

    void postInit();

    /**
     * Get the client player.
     *
     * @return The client player
     * @throws WrongSideException If called on the dedicated server.
     */
    @Nullable
    EntityPlayer getClientPlayer();

    /**
     * Get the client {@link World}.
     *
     * @return The client World
     * @throws WrongSideException If called on the dedicated server.
     */
    @Nullable
    World getClientWorld();

    /**
     * Get the {@link EntityPlayer} from the {@link MessageContext}.
     *
     * @param context The message context
     * @return The player
     */
    EntityPlayer getPlayer(MessageContext context);

    /**
     * Get the {@link IThreadListener} for the {@link MessageContext}'s {@link net.minecraftforge.fml.relauncher.Side}.
     *
     * @param context The message context
     * @return The thread listener
     */
    IThreadListener getThreadListener(MessageContext context);

    /**
     * Display the custom ui.
     *
     * @throws WrongSideException If called on the dedicated server.
     */
    void displayCustomUi();

    /**
     * Thrown when a proxy method is called from the wrong side.
     */
    class WrongSideException extends RuntimeException {
        public WrongSideException(final String message) {
            super(message);
        }

        public WrongSideException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
