/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.greenhouse;

import forestry.api.multiblock.IGreenhouseController;
import net.minecraft.nbt.NBTTagCompound;

public class DefaultGreenhouseLogic implements IGreenhouseLogic {

	public final IGreenhouseController controller;
	private final String name;

	public DefaultGreenhouseLogic(IGreenhouseController controller, String name) {
		this.controller = controller;
		this.name = name;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
	}

	@Override
	public void work() {
	}

	@Override
	public void onEvent(EnumGreenhouseEventType type, Object event) {
	}

	@Override
	public IGreenhouseController getController() {
		return controller;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void onMachineAssembled() {
	}

	@Override
	public void onMachineDisassembled() {
	}

}
