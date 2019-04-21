package fabian.codecraft.util;

import fabian.codecraft.CodeCraftMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Iterator;

public class ServerUtils {
    public static void NotifyOPs(String message, Object... obs) {
        TextComponentTranslation chatcomponenttranslation = new TextComponentTranslation(message, obs);
        Iterator iterator = CodeCraftMod.Server.getPlayerList().getPlayers().iterator();

        while(iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer)iterator.next();
            if (entityplayer.isEntityAlive() && isOp(entityplayer)) {
                entityplayer.sendMessage(chatcomponenttranslation);
            }
        }
    }

    public static boolean isOp(EntityPlayer player) {
        return player.canUseCommand(2, "");
    }
}
