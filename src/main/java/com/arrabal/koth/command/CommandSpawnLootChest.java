package com.arrabal.koth.command;

import com.arrabal.koth.reference.Messages;
import com.arrabal.koth.reference.Names;
import com.arrabal.koth.world.storage.loot.ModLootTableList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.Random;

/**
 * Created by Arrabal on 3/22/2016.
 */
public class CommandSpawnLootChest extends CommandBase {

    @Override
    public String getCommandName() {
        return Names.Commands.COMMAND_GEN_LOOT_CHEST;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return Messages.Commands.GEN_LOOT_CHEST_USAGE;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 3){
            throw new WrongUsageException(Messages.Commands.GEN_LOOT_CHEST_USAGE);
        }

        BlockPos pos = new BlockPos(parseInt(args[1]), parseInt(args[2]), parseInt(args[3]));
        IBlockState chest = Blocks.chest.getDefaultState();
        sender.getEntityWorld().setBlockState(pos, Blocks.chest.correctFacing(sender.getEntityWorld(), pos, chest), 2);
        TileEntity teChest = sender.getEntityWorld().getTileEntity(pos);
        if (teChest instanceof TileEntityChest){
            ((TileEntityChest)teChest).setLoot(ModLootTableList.CHESTS_VILLAGE_ABANDONED_HOUSE, new Random(sender.getEntityWorld().getTotalWorldTime()).nextLong());
            //((TileEntityChest)teChest).setLoot(LootTableList.CHESTS_VILLAGE_BLACKSMITH, new Random(sender.getEntityWorld().getTotalWorldTime()).nextLong());
        }
    }
}