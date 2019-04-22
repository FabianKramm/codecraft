package fabian.codecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemTest extends Item {
    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote) {
            final IBlockState state = world.getBlockState(pos);
            final Block block = state.getBlock();
            final ResourceLocation location = block.getRegistryName();

            if (location != null) {
                player.sendMessage(new TextComponentTranslation("Delete block " + location.toString()));
            }

            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
            world.notifyBlockUpdate(pos, state, state, 3);
        }


        return EnumActionResult.PASS;
    }
}
