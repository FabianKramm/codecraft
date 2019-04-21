package fabian.codecraft.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomAi extends EntityAIBase {
    protected EntityLiving entity;
    protected int ticks = 0;
    private static final Logger LOGGER = LogManager.getLogger();

    public CustomAi(EntityLiving entityIn)
    {
        this.entity = entityIn;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        ++ticks;
        if (ticks >= 40) {
            LOGGER.info("Should execute");
            ticks = 0;
        }

        EntityPlayer player = entity.world.getClosestPlayer(entity.posX, entity.posY, entity.posZ, 10.0D, false);

        return player != null && entity.getAttackTarget() == null && !entity.isDead;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        EntityPlayer player = entity.world.getClosestPlayer(entity.posX, entity.posY, entity.posZ, 10.0D, false);
        boolean shouldContinue = player != null && entity.getAttackTarget() == null && !entity.isDead;

        ++ticks;
        if (shouldContinue && ticks >= 40) {
            LOGGER.info("Should continue executing");
            entity.getJumpHelper().setJumping();

            ticks = 0;
        }

        return shouldContinue;
    }
}
