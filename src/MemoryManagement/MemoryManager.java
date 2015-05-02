package MemoryManagement;

import java.util.ArrayList;

public class MemoryManager {

	Tree tree;
	int maxSize;
	ArrayList<Tree> leaves;
	
	public MemoryManager(int maxSize)
	{
		this.maxSize = maxSize;
		leaves = new ArrayList<Tree>();
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
		leaves.remove(t);
		if(t.getParent().getRight() == null && t.getParent().getLeft() == null)
			leaves.add(t.getParent());
		t.setLp(null);
		t = null;
		tree.deallocate();
	}
	public String toString()
	{
		return leaves.toString();
	}
}
