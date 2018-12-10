package com.imt.infinityscience.blocks;

import com.imt.infinityscience.InfinityScience;
import com.imt.infinityscience.blocks.blocksubclasses.ItemBlockVarients;
import com.imt.infinityscience.items.InfinityItems;
import com.imt.infinityscience.util.handlers.EnumHandler;
import com.imt.infinityscience.util.handlers.EnumHandler.EnumType;
import com.imt.infinityscience.util.interfaces.IHasModel;
import com.imt.infinityscience.util.interfaces.IMetaName;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class InfinityOres extends Block implements IHasModel, IMetaName
{
	public static final PropertyEnum<EnumHandler.EnumType> VARIENT = PropertyEnum.<EnumType>create("varient", EnumHandler.EnumType.class);
	
	private String name, dimension;
	
	public InfinityOres(String name, String dimension) 
	{
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(InfinityScience.infinitytab);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIENT, EnumHandler.EnumType.copper));
		
		this.name = name;
		this.dimension = dimension;
		
		InfinityBlocks.BLOCKS.add(this);
		InfinityItems.ITEMS.add(new ItemBlockVarients(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return ((EnumHandler.EnumType)state.getValue(VARIENT)).getMeta();
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumHandler.EnumType)state.getValue(VARIENT)).getMeta();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIENT, EnumHandler.EnumType.byMetadata(meta));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for(EnumHandler.EnumType varient : EnumHandler.EnumType.values()) 
		{
			items.add(new ItemStack(this, 1, varient.getMeta()));
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {VARIENT});
	}
	
	@Override public String getSpecialName(ItemStack  stack) {
		return EnumHandler.EnumType.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	public void registerModels() 
	{
		for(int i = 0; i < EnumHandler.EnumType.values().length; i++)
		{
			InfinityScience.proxy.registerVarientRenderer(Item.getItemFromBlock(this), i, "ore_" + this.dimension + "_" + EnumHandler.EnumType.values()[i].getName(), "inventory");
		}
	}
}
