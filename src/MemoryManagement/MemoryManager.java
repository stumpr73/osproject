package MemoryManagement;

import java.util.ArrayList;

public class MemoryManager {
//chasdf
	private TreeManager treeManager;
	private ArrayList<Tree> leaves;
	
	public MemoryManager()
	{
		leaves = new ArrayList<Tree>();
		treeManager = new TreeManager(64, leaves);
	}
	
	public void allocate(Process p)
	{
		treeManager.allocate(p);
	}
	
	public void deallocate(Process p)
	{
		treeManager.deallocate(p);
		
	}
	
	public ArrayList<Tree> getProcesses()
	{
		return leaves;
	}
	
	public String toString()
	{
		return leaves.toString();
	}
}
