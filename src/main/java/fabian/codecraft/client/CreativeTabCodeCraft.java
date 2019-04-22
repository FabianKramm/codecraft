package fabian.codecraft.client;

import fabian.codecraft.CodeCraftMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabCodeCraft extends CreativeTabs {
    public CreativeTabCodeCraft() {
        super(CodeCraftMod.MODID);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.STONE_SWORD);
    }
}