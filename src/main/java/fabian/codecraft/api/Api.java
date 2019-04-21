package fabian.codecraft.api;

import fabian.codecraft.CodeCraftMod;
import fabian.codecraft.controller.ScriptContainer;
import fabian.codecraft.network.MessageOpenCustomUi;
import fabian.codecraft.util.ServerUtils;
import net.minecraft.entity.player.EntityPlayerMP;

public class Api {
    public static void log(String s) {
        ServerUtils.NotifyOPs(s);
    }

    public static void displayCustomUi() {
        if (ScriptContainer.CurrentExecutor instanceof EntityPlayerMP) {
            CodeCraftMod.network.sendTo(new MessageOpenCustomUi(), (EntityPlayerMP) ScriptContainer.CurrentExecutor);
        }
    }
}
