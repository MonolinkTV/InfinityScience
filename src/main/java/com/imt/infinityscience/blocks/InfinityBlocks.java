package com.imt.infinityscience.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InfinityBlocks
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block BLOCK_MARBLE =  new BlockBase("block_marble", Material.GROUND);
	
	//Ores
	public static final Block BLOCK_COPPERORE =  new BlockBase("block_copperore", Material.GROUND);
	public static final Block BLOCK_TINORE =  new BlockBase("block_tinore", Material.GROUND);
	
	public static final Block ORE_OVERWORLD = new InfinityOres("ore_overworld", "overworld");
}