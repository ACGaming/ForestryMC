package forestry.factory.recipes.jei.squeezer;

import java.util.ArrayList;
import java.util.List;

import forestry.api.recipes.ISqueezerRecipe;
import forestry.api.recipes.RecipeManagers;

public class SqueezerRecipeMaker {

	private SqueezerRecipeMaker() {
	}
	
	public static List<SqueezerRecipeWrapper> getSqueezerRecipes() {
		List<SqueezerRecipeWrapper> recipes = new ArrayList<>();
		for (ISqueezerRecipe recipe : RecipeManagers.squeezerManager.recipes()) {
			recipes.add(new SqueezerRecipeWrapper(recipe));
		}
		return recipes;
	}
	
	public static List<SqueezerContainerRecipeWrapper> getSqueezerContainerRecipes() {
		List<SqueezerContainerRecipeWrapper> recipes = new ArrayList<>();
//		for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData()) {
//			ISqueezerContainerRecipe containerRecipe = SqueezerRecipeManager.findMatchingContainerRecipe(data.filledContainer);
//			if (containerRecipe != null) {
//				recipes.add(new SqueezerContainerRecipeWrapper(containerRecipe, data.filledContainer));
//			}
//		}
		return recipes;
	}
	
}
