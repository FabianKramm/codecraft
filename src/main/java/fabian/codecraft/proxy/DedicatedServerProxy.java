package fabian.codecraft.proxy;

import fabian.codecraft.controller.ScriptController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nullable;

public class DedicatedServerProxy implements IProxy {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    @Nullable
    @Override
    public EntityPlayer getClientPlayer() {
        throw new WrongSideException("Tried to get the client player on the dedicated server");
    }

    @Nullable
    @Override
    public World getClientWorld() {
        throw new WrongSideException("Tried to get the client world on the dedicated server");
    }

    @Override
    public EntityPlayer getPlayer(final MessageContext context) {
        if (context.side.isServer()) {
            return context.getServerHandler().player;
        } else {
            throw new WrongSideException("Tried to get the player from a client-side MessageContext on the dedicated server");
        }
    }

    @Override
    public IThreadListener getThreadListener(final MessageContext context) {
        if (context.side.isServer()) {
            return context.getServerHandler().player.mcServer;
        } else {
            throw new WrongSideException("Tried to get the IThreadListener from a client-side MessageContext on the dedicated server");
        }
    }

    @Override
    public void displayCustomUi() {
        throw new WrongSideException("Tried to open the custom ui on the dedicated server");
    }
}
