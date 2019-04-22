package fabian.codecraft.api;

import fabian.codecraft.util.PathUtil;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import noppes.npcs.api.entity.IEntityLiving;

public class Movement {
    public void navigateOrTpTo(IEntityLiving entityLiving, double x, double y, double z, double speed) {
        Path path = entityLiving.getMCEntity().getNavigator().getPathToXYZ(x, y, z);

        if(path != null && path.getFinalPathPoint() != null) {
            PathPoint pathPoint = path.getFinalPathPoint();

            if(PathUtil.manhatten((int)x, (int)y, (int)z, pathPoint.x, pathPoint.y, pathPoint.z) < 2) {
                entityLiving.getMCEntity().getNavigator().clearPath();
                entityLiving.getMCEntity().getNavigator().setPath(path, speed);
                return;
            }
        }

        entityLiving.getMCEntity().setPosition(x, y, z);
        entityLiving.getMCEntity().getNavigator().clearPath();
    }
}
