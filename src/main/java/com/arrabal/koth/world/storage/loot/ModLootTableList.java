package com.arrabal.koth.world.storage.loot;

import com.arrabal.koth.reference.Names;
import com.arrabal.koth.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

/**
 * Created by Arrabal on 3/22/2016.
 */
public class ModLootTableList {

    public static final ResourceLocation CHESTS_VILLAGE_ABANDONED_HOUSE = LootTableList.register(new ResourceLocation(Reference.MOD_ID, Names.LootTables.ABANDONED_VIL_HOUSE));
}
