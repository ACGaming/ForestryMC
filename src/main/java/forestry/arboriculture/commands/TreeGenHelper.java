/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.arboriculture.commands;

import forestry.api.arboriculture.IAlleleTreeSpecies;
import forestry.api.arboriculture.ITree;
import forestry.api.arboriculture.ITreeGenome;
import forestry.api.arboriculture.TreeManager;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.core.commands.SpeciesNotFoundException;
import forestry.core.commands.TemplateNotFoundException;
import forestry.core.utils.BlockUtil;
import forestry.core.worldgen.WorldGenBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public final class TreeGenHelper {

	public static WorldGenerator getWorldGen(String treeName, EntityPlayer player, BlockPos pos) throws SpeciesNotFoundException, TemplateNotFoundException {
		ITreeGenome treeGenome = getTreeGenome(treeName);
		ITree tree = TreeManager.treeRoot.getTree(player.world, treeGenome);
		return tree.getTreeGenerator(player.world, pos, true);
	}

	public static void generateTree(WorldGenerator gen, EntityPlayer player, BlockPos pos) {
		World world = player.world;

		if (world.isAirBlock(pos.down())) {
			pos = BlockUtil.getNextSolidDownPos(world, pos);
		} else {
			pos = BlockUtil.getNextReplaceableUpPos(world, pos);
		}

		IBlockState blockState = world.getBlockState(pos);
		if (BlockUtil.canPlaceTree(blockState, world, pos)) {
			if (gen instanceof WorldGenBase) {
				((WorldGenBase) gen).generate(world, world.rand, pos, true);
			} else {
				gen.generate(world, world.rand, pos);
			}
		}
	}

	private static ITreeGenome getTreeGenome(String speciesName) throws SpeciesNotFoundException, TemplateNotFoundException {
		IAlleleTreeSpecies species = null;

		for (String uid : AlleleManager.alleleRegistry.getRegisteredAlleles().keySet()) {

			if (!uid.equals(speciesName)) {
				continue;
			}

			IAllele allele = AlleleManager.alleleRegistry.getAllele(uid);
			if (allele instanceof IAlleleTreeSpecies) {
				species = (IAlleleTreeSpecies) allele;
				break;
			}
		}

		if (species == null) {
			for (IAllele allele : AlleleManager.alleleRegistry.getRegisteredAlleles().values()) {
				if (allele instanceof IAlleleTreeSpecies && allele.getName().replaceAll("\\s", "").equals(speciesName)) {
					species = (IAlleleTreeSpecies) allele;
					break;
				}
			}
		}

		if (species == null) {
			throw new SpeciesNotFoundException(speciesName);
		}

		IAllele[] template = TreeManager.treeRoot.getTemplate(species);

		return TreeManager.treeRoot.templateAsGenome(template);
	}
}
