package com.imt.infinityscience.recipes;

import com.imt.infinityscience.blocks.InfinityBlocks;
import com.imt.infinityscience.items.InfinityItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InfinityRecipes {

	public static void init() {
		GameRegistry.addSmelting(InfinityBlocks.BLOCK_COPPERORE, new ItemStack(InfinityItems.INGOT_COPPER, 1), 1.5f);
		GameRegistry.addSmelting(InfinityBlocks.BLOCK_TINORE, new ItemStack(InfinityItems.INGOT_TIN, 1), 1.5f);
	}
}
