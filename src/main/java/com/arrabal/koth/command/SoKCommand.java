package com.arrabal.koth.command;

import akka.io.Tcp;
import com.arrabal.koth.reference.Messages;
import com.arrabal.koth.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arrabal on 2/22/2016.
 */
public class SoKCommand extends CommandBase {

    private static List<CommandBase> modCommands = new ArrayList<CommandBase>();
    private static List<String> commands = new ArrayList<String>();

    @Override
    public String getCommandName() {
        return Names.Commands.BASE_COMMAND;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return Messages.Commands.BASE_COMMAND_USAGE;
    }

    @Override
    public int getRequiredPermissionLevel(){
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length >= 1){
            for (CommandBase command : modCommands){
                if (command.getCommandName().equalsIgnoreCase(args[0]) && command.checkPermission(server, sender)){
                    command.execute(server, sender, args);
                }
            }
        }
        else{
            throw new WrongUsageException(this.getCommandUsage(sender));
        }
    }

    static{
        //add commands to modCommand here
        modCommands.add(new CommandSpawnLootChest());

        for (CommandBase commandBase : modCommands){
            commands.add(commandBase.getCommandName());
        }
    }
}
