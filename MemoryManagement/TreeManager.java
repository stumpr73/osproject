package MemoryManagement;

import java.util.ArrayList;

public class TreeManager {
//chasdf
	private Tree tree;
	private ArrayList<Tree> leaves;
	
	public TreeManager(int maxSize, ArrayList<Tree> leaves)
	{

		this.leaves = leaves;
		tree = new Tree(maxSize, null, 0, leaves);
	}
	
	public void allocate(Process p)
	{
		tree.allocate(p);
	}
	
	public void deallocate(Process p)
	{
		Tree t = null;
		for(int i = 0; i < leaves.size(); i++)
		{
			if(leaves.get(i).getLp() == null)
				;
			else if(leaves.get(i).getLp().getName() == p.getName())
				t = leaves.get(i);
		}
		
		if(t == null)
		{
			return;
		}
		
		maintainTree(t);
		
		System.out.println("done");
		
	}
	
	public void maintainTree(Tree t)
	{
		if(t.getParent() == null)
			return;
		//at this point, 
		//t is the tree node we need to remove the process from
		
		if(t.getSibling() == null)
		{
			System.out.println("Error. Look in the deallocate method in Memory Manager");
		}
		else if(t.getSibling().getLp() == null && t.getSibling().getLeft() == null)
		{
			
			leaves.add(t.getParent());
			leaves.remove(t.getSibling());
			leaves.remove(t);
			t.getParent().resetChildren();
			if(t.getParent() != null)
				maintainTree(t.getParent());
		}
		else
		{
			t.setLp(null);
			
		}
	}
	
	public ArrayList<Tree> getLeaves()
	{
		return leaves;
	}
	
	public String toString()
	{
		return leaves.toString();
	}
}