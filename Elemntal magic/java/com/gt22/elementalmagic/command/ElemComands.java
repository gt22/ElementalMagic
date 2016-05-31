package com.gt22.elementalmagic.command;

import java.util.ArrayList;
import java.util.List;

import com.gt22.elementalmagic.api.VoidMatrixApi;
import com.gt22.elementalmagic.enums.MatrixType;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
	    return null;
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
			player = getPlayer(sender, args[3]);
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
				sender.addChatMessage(new ChatComponentTranslation("/em matrix add/set/draw stable/unstable player amount", new Object[0]));
				break;
			}
			case("matrix"):
			{
				if(player == null)
				{
					sender.addChatMessage(new ChatComponentTranslation("Unable to find player", new Object[0]));
					return;
				}
				switch(args[1].toLowerCase())
				{
				case("add"):
				{
					switch(args[2].toLowerCase())
					{
					case("stable"):
					{
						try
						{
							int amount = Integer.parseInt(args[4]);
							VoidMatrixApi.addMatrix(MatrixType.STABLE, amount, player);
							sender.addChatMessage(new ChatComponentTranslation("Added " + args[4] + " to " + args[2].toLowerCase() + " matrix of " + args[3], new Object[0]));
							return;
						}
						catch(Exception e)
						{
							sender.addChatMessage(new ChatComponentTranslation("wrong argument 5, must be a number", new Object[0]));
							return;
						}	
					}
					case("unstable"):
					{
						try
						{
							int amount = Integer.parseInt(args[4]);
							VoidMatrixApi.addMatrix(MatrixType.UNSTABLE, amount, player);
							sender.addChatMessage(new ChatComponentTranslation("Added " + args[4] + " to " + args[2].toLowerCase() + " matrix of " + args[3], new Object[0]));
							return;
						}
						catch(Exception e)
						{
							sender.addChatMessage(new ChatComponentTranslation("wrong argument 5, must be a number", new Object[0]));
							return;
						}	
					}
					case("both"):
					{
						try
						{
							int amount = Integer.parseInt(args[4]);
							VoidMatrixApi.addMatrix(MatrixType.STABLE, amount, player);
							VoidMatrixApi.addMatrix(MatrixType.UNSTABLE, amount, player);
							sender.addChatMessage(new ChatComponentTranslation("Added " + args[4] + " to " + args[2].toLowerCase() + " matrices of " + args[3], new Object[0]));
							return;
						}
						catch(Exception e)
						{
							sender.addChatMessage(new ChatComponentTranslation("wrong argument 5, must be a number", new Object[0]));
							return;
						}	
					}
					default:
					{
						sender.addChatMessage(new ChatComponentTranslation("wrong argument 3, must be a stable/unstable", new Object[0]));
						return;
					}
					}
				}
				case("set"):
				{
					switch(args[2].toLowerCase())
					{
					case("stable"):
					{
						try
						{
							int amount = Integer.parseInt(args[4]);
							VoidMatrixApi.setMatrix(MatrixType.STABLE, amount, player);
							sender.addChatMessage(new ChatComponentTranslation("Setted " + args[2].toLowerCase() + " matrix of " + args[3] + " to " + args[4], new Object[0]));
							return;
						}
						catch(Exception e)
						{
							sender.addChatMessage(new ChatComponentTranslation("wrong argument 5, must be a number", new Object[0]));
							return;
						}	
					}
					case("unstable"):
					{
						try
						{
							int amount = Integer.parseInt(args[4]);
							VoidMatrixApi.setMatrix(MatrixType.UNSTABLE, amount, player);
							sender.addChatMessage(new ChatComponentTranslation("Setted " + args[2].toLowerCase() + " matrix of " + args[3] + " to " + args[4], new Object[0]));
							return;
						}
						catch(Exception e)
						{
							sender.addChatMessage(new ChatComponentTranslation("wrong argument 5, must be a number", new Object[0]));
							return;
						}	
					}
					case("both"):
					{
						try
						{
							int amount = Integer.parseInt(args[4]);
							VoidMatrixApi.setMatrix(MatrixType.STABLE, amount, player);
							VoidMatrixApi.setMatrix(MatrixType.UNSTABLE, amount, player);
							sender.addChatMessage(new ChatComponentTranslation("Setted " + args[2].toLowerCase() + " matrices of " + args[3] + " to " + args[4], new Object[0]));
							return;
						}
						catch(Exception e)
						{
							sender.addChatMessage(new ChatComponentTranslation("wrong argument 5, must be a number", new Object[0]));
							return;
						}	
					}
					default:
					{
						sender.addChatMessage(new ChatComponentTranslation("wrong argument 3, must be a stable/unstable", new Object[0]));
						return;
					}
					}
				}
				case("draw"):
				{
					switch(args[2].toLowerCase())
					{
					case("stable"):
					{
						try
						{
							int amount = Integer.parseInt(args[4]);
							VoidMatrixApi.drawMatrix(MatrixType.STABLE, amount, player);
							sender.addChatMessage(new ChatComponentTranslation("Drawed " + args[4] + " from " + args[2].toLowerCase() + " matrix of " + args[3], new Object[0]));
							return;
						}
						catch(Exception e)
						{
							sender.addChatMessage(new ChatComponentTranslation("wrong argument 5, must be a number", new Object[0]));
							return;
						}	
					}
					case("unstable"):
					{
						try
						{
							int amount = Integer.parseInt(args[4]);
							VoidMatrixApi.drawMatrix(MatrixType.UNSTABLE, amount, player);
							sender.addChatMessage(new ChatComponentTranslation("Drawed " + args[4] + " from " + args[2].toLowerCase() + " matrix of " + args[3], new Object[0]));
							return;
						}
						catch(Exception e)
						{
							sender.addChatMessage(new ChatComponentTranslation("wrong argument 5, must be a number", new Object[0]));
							return;
						}	
					}
					case("both"):
					{
						try
						{
							int amount = Integer.parseInt(args[4]);
							VoidMatrixApi.drawMatrix(MatrixType.STABLE, amount, player);
							VoidMatrixApi.drawMatrix(MatrixType.UNSTABLE, amount, player);
							sender.addChatMessage(new ChatComponentTranslation("Drawed " + args[4] + " from " + args[2].toLowerCase() + " matrices of " + args[3], new Object[0]));
							return;
						}
						catch(Exception e)
						{
							sender.addChatMessage(new ChatComponentTranslation("wrong argument 5, must be a number", new Object[0]));
							return;
						}	
					}
					default:
					{
						sender.addChatMessage(new ChatComponentTranslation("wrong argument 3, must be a stable/unstable", new Object[0]));
						return;
					}
					}
				}
				default:
				{
					sender.addChatMessage(new ChatComponentTranslation("wrong argument 2, must be a add/set/draw", new Object[0]));
					return;
				}
				}
			}
			default:
			{
				sender.addChatMessage(new ChatComponentTranslation("No such command, use /em help", new Object[0]));
				return;
			}
			}
		
	}

}
