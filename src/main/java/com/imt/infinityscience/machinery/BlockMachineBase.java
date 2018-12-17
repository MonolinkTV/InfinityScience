package com.imt.infinityscience.machinery;

import com.imt.infinityscience.InfinityScience;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineBase extends BlockContainer{
	
	public BlockMachineBase()
	{
		super(Material.IRON);
		setHardness(3.5F);
		setResistance(16F);
		setCreativeTab(InfinityScience.infinitytab);
}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return null;
	}

}
