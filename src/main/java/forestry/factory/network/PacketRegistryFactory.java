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
package forestry.factory.network;

import forestry.core.network.PacketRegistry;
import forestry.factory.network.packets.PacketRecipeTransferRequest;
import forestry.factory.network.packets.PacketRecipeTransferUpdate;
import forestry.factory.network.packets.PacketWorktableMemoryUpdate;
import forestry.factory.network.packets.PacketWorktableRecipeRequest;
import forestry.factory.network.packets.PacketWorktableRecipeUpdate;

public class PacketRegistryFactory extends PacketRegistry {
	@Override
	public void registerPackets() {

		registerClientPacket(new PacketWorktableMemoryUpdate());
		registerClientPacket(new PacketWorktableRecipeUpdate());
		registerClientPacket(new PacketRecipeTransferUpdate());

		registerServerPacket(new PacketWorktableRecipeRequest());
		registerServerPacket(new PacketRecipeTransferRequest());
	}
}
