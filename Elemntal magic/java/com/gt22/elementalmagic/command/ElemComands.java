package com.gt22.elementalmagic.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class ElemComands extends CommandBase {

	private List aliases;
	
	  public ElemComands()
	  {
	    this.aliases = new ArrayList();
	    this.aliases.add("elemmagic");
	    this.aliases.add("elemm");
	    this.aliases.add("em");
	  }
	
	  public String getCommandName()
	  {
	    return "ElementalMagcic";
	  }
	  
	  public String getCommandUsage(ICommandSender icommandsender)
	  {
	    return "/ElementalMagcic <action> [<player> [<params>]]";
	  }
	  
	  public List getCommandAliases()
	  {
	    return this.aliases;
	  }
	  
	  public int getRequiredPermissionLevel()
	  {
	    return 2;
	  }
	  
	  public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring)
	  {
		  switch(astring.length)
		  {
			  case(1):
			  {
				  return getListOfStringsMatchingLastWord(astring, new String[] {"help"});
			  }
			  case(2):
			  {
				  return getListOfStringsMatchingLastWord(astring, new String[] {});
			  }
			  case(3):
			  {
				  return getListOfStringsMatchingLastWord(astring, new String[] {});
			  }
			  case(4):
			  {
				  String[] players = getListOfPlayerUsernames();
				  String[] ret = new String[players.length + 1];
				  for(int i = 0; i < players.length; i++)
				  {
					  ret[i] = players[i];
				  }
				  ret[players.length] = "me";
				  return getListOfStringsMatchingLastWord(astring, ret);
			  }
		  }
	    return null;
	  }
	  
	    protected String[] getListOfPlayerUsernames()
	    {
	        return MinecraftServer.getServer().getAllUsernames();
	    }
	  
	  public boolean isUsernameIndex(String[] astring, int i)
	  {
	    return i == 1;
	  }

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		EntityPlayerMP player = null;
		if(args.length > 3)
		{
			if(args[3].equals("me"))
			{
				player = getCommandSenderAsPlayer(sender);
			}
			else
			{
				player = getPlayer(sender, args[3]);
			}
		}
		
			if(args.length == 0)
			{
				sender.addChatMessage(new ChatComponentTranslation("Invalid arguments", new Object[0]));
				sender.addChatMessage(new ChatComponentTranslation("Use /ElementalMagcic help to get help", new Object[0]));
			}
			
			switch (args[0].toLowerCase())
			{
				case("help"):
				{
					sender.addChatMessage(new ChatComponentTranslation("Instead of ElementalMagic you can also use elemmagic, elemm or em", new Object[0]));
					break;
				}
				default:
				{
					sender.addChatMessage(new ChatComponentTranslation("No such command, use /em help", new Object[0]));
					return;
				}
			}
		
	}

}
