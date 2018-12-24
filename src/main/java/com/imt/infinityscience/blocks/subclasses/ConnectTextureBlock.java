package com.imt.infinityscience.blocks.subclasses;

import com.imt.infinityscience.blocks.BlockBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ConnectTextureBlock extends BlockBase
{

	public ConnectTextureBlock(String name, Material material) 
	{
		super(name, material);
	}
	
}