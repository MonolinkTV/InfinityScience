package com.imt.infinityscience.blocks;

import java.util.ArrayList;
import java.util.List;

import com.imt.infinityscience.blocks.subclasses.ConnectTextureBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InfinityBlocks
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	
	//Decorative Basic
	public static final Block BLOCK_MARBLE =  new BlockBase("block_marble", Material.GROUND);
	public static final Block BLOCK_BASALT = new BlockBase("block_basalt", Material.GROUND);
	
	//Decorative Tiled
	public static final Block BASALT_TILED = new ConnectTextureBlock("basalt_tiled", Material.GROUND);
	public static final Block MARBLE_TILED = new ConnectTextureBlock("marble_tiled", Material.GROUND);

	
	
	//Ores
	public static final Block BLOCK_COPPERORE =  new BlockBase("block_copperore", Material.ROCK);
	public static final Block BLOCK_TINORE =  new BlockBase("block_tinore", Material.ROCK);
	public static final Block BLOCK_IRIDIUMORE =  new BlockBase("block_iridiumore", Material.ROCK);
}