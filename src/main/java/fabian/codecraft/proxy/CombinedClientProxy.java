package fabian.codecraft.proxy;

import fabian.codecraft.client.gui.GuiCustomUi;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nullable;

public class CombinedClientProxy implements IProxy {
    private final Minecraft MINECRAFT = Minecraft.getMinecraft();

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
        return MINECRAFT.player;
    }

    @Nullable
    @Override
    public World getClientWorld() {
        return MINECRAFT.world;
    }

    @Override
    public EntityPlayer getPlayer(final MessageContext context) {
        if (context.side.isClient()) {
            return MINECRAFT.player;
        } else {
            return context.getServerHandler().player;
        }
    }

    @Override
    public IThreadListener getThreadListener(final MessageContext context) {
        if (context.side.isClient()) {
            return MINECRAFT;
        } else {
            return context.getServerHandler().player.mcServer;
        }
    }

    @Override
    public void displayCustomUi() {
        MINECRAFT.displayGuiScreen(new GuiCustomUi());
    }
}
