package ibuilder99.mods.cll.blocks;

import ibuilder99.mods.cll.CombustibleLemonLauncher;
import ibuilder99.mods.cll.items.LemonLauncherItems;
import ibuilder99.mods.cll.util.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LemonLeaves extends BlockLeavesBase {

	public String name;
	@SideOnly(Side.CLIENT)
	private static boolean isFancy;
	@SideOnly(Side.CLIENT)
	private Icon fastGraphics; 

	public LemonLeaves(int id, String unloc, String loc_en, float hardness, float resistance, StepSound sound) {
		super(id, Material.leaves, true);
		name = unloc;
		setUnlocalizedName(unloc);
		setHardness(hardness);
		setResistance(resistance);
		setCreativeTab(CombustibleLemonLauncher.tabLemonLauncher);
		setStepSound(sound);
		LanguageRegistry.addName(this, loc_en);
		GameRegistry.registerBlock(this, unloc);
	}
	
	@SideOnly(Side.CLIENT)
	public static void setGraphicsSetting(boolean setting){
		isFancy = setting;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + name);
		fastGraphics = par1IconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + name + "Opaque");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2){
		return isFancy ? blockIcon : fastGraphics;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube(){
		return !isFancy;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass(){
		return isFancy ? 0 : 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock(){
		return !isFancy;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
		return isFancy ? true : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
	}

	@Override
	public void breakBlock(World par1World, int x, int y, int z, int par5, int par6){
		super.breakBlock(par1World, x, y, z, par5, par6);
		if(!par1World.isRemote){
			EntityItem item = new EntityItem(par1World);
			item.setEntityItemStack(new ItemStack(LemonLauncherItems.Lemon));
			item.setPosition(x, y, z);
			par1World.spawnEntityInWorld(item);
			if(par1World.rand.nextInt(10) == 5){
				EntityItem sapling = new EntityItem(par1World);
				sapling.setEntityItemStack(new ItemStack(LemonLauncherBlocks.LemonSapling));
				sapling.setPosition(x, y, z);
				par1World.spawnEntityInWorld(sapling);
			}
		}
	}

}
