package fabian.codecraft.api;

import fabian.codecraft.CodeCraftMod;
import fabian.codecraft.controller.ScriptContainer;
import fabian.codecraft.network.MessageOpenCustomUi;
import fabian.codecraft.util.ServerUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentTranslation;

public class Api {
    public static Api Instance;

    public Ai ai = new Ai();
    public Movement movement = new Movement();

    public Api() {
        if (Instance == null) {
            Instance = this;
        }
    }

    public void log(String s) {
        if (ScriptContainer.CurrentExecutor instanceof EntityPlayerMP) {
            ScriptContainer.CurrentExecutor.sendMessage(new TextComponentTranslation(s));
        }
    }

    public void displayCustomUi() {
        if (ScriptContainer.CurrentExecutor instanceof EntityPlayerMP) {
            CodeCraftMod.network.sendTo(new MessageOpenCustomUi(), (EntityPlayerMP) ScriptContainer.CurrentExecutor);
        }
    }
}
