package forestry.factory.recipes.jei.bottler;

import javax.annotation.Nonnull;

import forestry.core.recipes.jei.ForestryRecipeCategory;
import forestry.core.recipes.jei.ForestryRecipeCategoryUid;
import forestry.core.render.ForestryResource;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class BottlerRecipeCategory extends ForestryRecipeCategory<BottlerRecipeWrapper> {

	private static final int emptySlot = 0;
	private static final int outputSlot = 1;
	private static final int inputTank = 2;
	
	private final static ResourceLocation guiTexture = new ForestryResource("textures/gui/bottler.png");
	@Nonnull
	private final IDrawableAnimated arrow;
	@Nonnull
	private final IDrawable tankOverlay;
	
	public BottlerRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createDrawable(guiTexture, 52, 16, 81, 60), "tile.for.bottler.name");
		
		IDrawableStatic arrowDrawable = guiHelper.createDrawable(guiTexture, 176, 74, 24, 17);
		this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.LEFT, false);
		this.tankOverlay = guiHelper.createDrawable(guiTexture, 176, 0, 16, 58);
	}

	@Nonnull
	@Override
	public String getUid() {
		return ForestryRecipeCategoryUid.BOTTLER;
	}

	@Override
	public void drawAnimations(@Nonnull Minecraft minecraft) {
		arrow.draw(minecraft, 28, 23);
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull BottlerRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
		
		guiItemStacks.init(emptySlot, true, 63, 2);
		guiItemStacks.init(outputSlot, false, 63, 38);
		
		guiFluidStacks.init(inputTank, true, 1, 1, 16, 58, 10000, false, tankOverlay);

		guiItemStacks.set(ingredients);
		guiFluidStacks.set(ingredients);
	}

}
