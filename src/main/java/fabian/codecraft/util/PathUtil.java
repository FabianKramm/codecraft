package fabian.codecraft.util;

import net.minecraft.entity.Entity;

public class PathUtil {
    public static boolean isInRange(Entity from, Entity to, double range) {
        return isInRange(from, to.posX, to.posY, to.posZ, range);
    }

    private static boolean isInRange(Entity from, double posX, double posY, double posZ, double range) {
        double y = Math.abs(from.posY - posY);
        if (posY >= 0.0D && y > range) {
            return false;
        } else {
            double x = Math.abs(from.posX - posX);
            double z = Math.abs(from.posZ - posZ);
            return x <= range && z <= range;
        }
    }

    public static int manhatten(int x1, int y1, int z1, int x2, int y2, int z2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(z1 - z2);
    }
}
