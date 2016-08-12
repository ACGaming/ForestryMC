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
package forestry.lepidopterology.genetics.alleles;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import forestry.api.genetics.AlleleManager;
import forestry.api.lepidopterology.EnumButterflyChromosome;
import forestry.api.lepidopterology.IAlleleButterflyCocoon;
import forestry.core.PluginCore;
import forestry.core.config.Constants;
import forestry.core.genetics.alleles.AlleleCategorized;
import forestry.lepidopterology.blocks.property.PropertyCocoon;

public class AlleleButterflyCocoon extends AlleleCategorized implements IAlleleButterflyCocoon {
	
	public static final PropertyCocoon COCOON = new PropertyCocoon("cocoon");
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);
	
	public static IAlleleButterflyCocoon cocoonDefault;
	public static IAlleleButterflyCocoon cocoonSilk;
	private static List<IAlleleButterflyCocoon> cocoonAlleles;

	public static void createAlleles() {
		List<IAlleleButterflyCocoon> cocoonAlleles = Arrays.asList(
				cocoonDefault = new AlleleButterflyCocoon("default", false),
				cocoonSilk = new AlleleButterflyCocoon("silk", false)
		);

		for (IAlleleButterflyCocoon cocoonAllele : cocoonAlleles) {
			AlleleManager.alleleRegistry.registerAllele(cocoonAllele, EnumButterflyChromosome.COCOON);
		}
	}
	
	public static void createLoot(){
		cocoonDefault.getCocoonLoot().put(new ItemStack(Items.STRING, 2), 1F);
		cocoonDefault.getCocoonLoot().put(new ItemStack(Items.STRING), 0.75F);
		cocoonDefault.getCocoonLoot().put(new ItemStack(Items.STRING, 3), 0.25F);
		
		cocoonSilk.getCocoonLoot().put(new ItemStack(PluginCore.items.craftingMaterial, 3, 2), 0.75F);
		cocoonSilk.getCocoonLoot().put(new ItemStack(PluginCore.items.craftingMaterial, 2, 2), 0.25F);
	}
	
	private final Map<ItemStack, Float> loot = new HashMap<>();
	private final String name;
	
	public AlleleButterflyCocoon(String name, boolean isDominant) {
		super(Constants.MOD_ID, "cocoon", name, isDominant);
		this.name = name;
	}
	
	private static String getAgeKey(int age){
		if(age == 0){
			return "early";
		}else if(age == 1){
			return "middle";
		}else{
			return "late";
		}
	}
	
	@Override
	public String getCocoonName() {
		return name;
	}
	
	@Override
	public ModelResourceLocation getCocoonItemModel(int age) {
		return new ModelResourceLocation(Constants.MOD_ID + ":lepidopterology/cocoons/cocoon_" + name + "_" + getAgeKey(age), "inventory");
	}

	@Override
	public Map<ItemStack, Float> getCocoonLoot() {
		return loot;
	}
	
	@Override
	public int compareTo(@Nonnull IAlleleButterflyCocoon o) {
		return 0;
	}
	
}
