package com.gt22.elementalmagic.tiles;

import com.gt22.elementalmagic.api.AdvThaumApi;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import net.minecraft.tileentity.TileEntity;

public class TileSuperNode extends TileEntity implements INode
{
	private AspectList aspects = AdvThaumApi.getPrimals(600);
	private AspectList maxaspects = AdvThaumApi.getPrimals(600);
	
	@Override
	public AspectList getAspects()
	{
		return aspects;
	}

	@Override
	public void setAspects(AspectList aspects)
	{
		this.aspects = aspects;
	}

	@Override
	public boolean doesContainerAccept(Aspect tag)
	{
		return aspects.getAmount(tag) != 0;
	}

	@Override
	public int addToContainer(Aspect tag, int amount)
	{
		amount = Math.min(amount, maxaspects.getAmount(tag) - aspects.getAmount(tag));
		aspects.add(tag, amount);
		return amount;
	}

	@Override
	public boolean takeFromContainer(Aspect tag, int amount)
	{
		int takeamount = Math.min(amount, maxaspects.getAmount(tag) - aspects.getAmount(tag));
		aspects.reduce(tag, takeamount);
		return amount == takeamount;
	}

	@Override
	public boolean takeFromContainer(AspectList ot)
	{
		boolean succes = true;
		for(Aspect a : ot.getAspects())
		{
			succes = takeFromContainer(a, ot.getAmount(a)) && succes;
		}
		return succes;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect tag, int amount)
	{
		return aspects.getAmount(tag) > amount;
	}

	@Override
	public boolean doesContainerContain(AspectList ot)
	{
		boolean succes = true;
		for(Aspect a : ot.getAspects())
		{
			succes = doesContainerContainAmount(a, ot.getAmount(a)) && succes;
		}
		return succes;
	}

	@Override
	public int containerContains(Aspect tag)
	{
		return aspects.getAmount(tag);
	}

	@Override
	public String getId()
	{
		return worldObj.provider.dimensionId + ":" + xCoord + ":" + yCoord + ":" + zCoord;
	}

	@Override
	public AspectList getAspectsBase()
	{
		return maxaspects;
	}

	@Override
	public NodeType getNodeType()
	{
		return NodeType.NORMAL;
	}

	@Override
	public void setNodeType(NodeType nodeType)
	{
		
	}

	@Override
	public void setNodeModifier(NodeModifier nodeModifier)
	{
		
	}

	@Override
	public NodeModifier getNodeModifier()
	{
		return NodeModifier.BRIGHT;
	}

	@Override
	public int getNodeVisBase(Aspect aspect)
	{
		return maxaspects.getAmount(aspect);
	}

	@Override
	public void setNodeVisBase(Aspect aspect, short nodeVisBase)
	{
		maxaspects.remove(aspect);
		maxaspects.add(aspect, nodeVisBase);
	}

}
