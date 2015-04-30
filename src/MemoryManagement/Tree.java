package MemoryManagement;

import java.util.ArrayList;


public class Tree {

	private Tree left;
	private Tree right;
	private int size;
	private int sizeLeft; //the available space underneath this node
	private Process lp;
	private Tree parent;
	private int lowEnd;
	private ArrayList<Tree> leaves;
	

	public Tree(int s, Tree p, int lowEnd, ArrayList<Tree> leaves){
		size = s;
		left = null;
		right = null;
		lp = null;
		this.lowEnd=lowEnd;
		sizeLeft = size;
		parent = p;
		this.leaves = leaves;
		leaves.add(this);
	}
	
	
	public void allocate(Process p){
		Tree c = null;
		if(p.getSize()<= size/2){
			if(left==null && right == null){
				generateChildren();
			}
			c = findSuitableChild(p);
			if(c != null)
				c.allocate(p);
			else
				System.out.println("There is no more room in the tree");
		}
		else if(lp==null)
			assignProcess(p);
		else
			System.out.println("Cannot fit");
	}
	

	public void generateChildren(){
		leaves.remove(this);
		left = new Tree(size/2,this, lowEnd, leaves);
		right = new Tree(size/2,this, (lowEnd +(size/2)), leaves);
	}
	public Tree findSuitableChild(Process p){
		if(left.getSizeLeft() >= p.getSize() && left.getLp() == null)
			return left;
		else if(right.getSizeLeft() >= p.getSize() && right.getLp() ==null)
			return right;
		else
		{
			System.out.println("There is no more room in the tree");
			return null;
		}
	}

	public int getSizeLeft(){
		return sizeLeft;
	}
	
	/**
	 * 
	 * @param p
	 */
	public void assignProcess(Process p){
		int adjustedSize = (int)Math.pow(2,Math.ceil(Math.log(p.getSize())/Math.log(2)));
		lp = p;
		updateParentSize(adjustedSize);
		
		//System.out.println("The " + p.getName() + " was assigned to a node with size " + size);
	}
	
	public void updateParentSize(int s)
	{
		if(parent != null)
		{
			sizeLeft = sizeLeft - s;
			parent.updateParentSize(s);
		}
		else
		{
			sizeLeft = sizeLeft - s;
		}
	}

	public void deallocate(){
		
		if(left == null)
			return;
		else if(left.areNoChildren() && right.areNoChildren())
		{
			this.resetChildren();
			leaves.add(this);
		}
		else
		{
			left.deallocate();
			right.deallocate();
		}
		
	}


	public void resetChildren()
	{
		left = null;
		right = null;
		
	}

	public boolean hasParent()
	{
		if(parent == null)
			return false;
		return true;
	}
	
	public Tree getParent()
	{
		return parent;
	}
	
	public boolean areNoChildren()
	{
		return left == null;
	}

	public Tree getLeft() {
		return left;
	}
	public void setLeft(Tree left) {
		this.left = left;
	}
	public Tree getRight() {
		return right;
	}
	public void setRight(Tree right) {
		this.right = right;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Process getLp() {
		return lp;
	}
	public void setLp(Process lp) {
		this.lp = lp;
	}
	public void setParent(Tree parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tree other = (Tree) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (lp == null) {
			if (other.lp != null)
				return false;
		} else if (!lp.equals(other.lp))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (size != other.size)
			return false;
		if (sizeLeft != other.sizeLeft)
			return false;
		return true;
	}
	
	public String toString()
	{
		if(lp != null)
			return lp.getName() + " of size " + size + " Range: " + lowEnd + " to "+ (lowEnd + size -1) + "\n";
		return "Size: " + size + " Range: " + lowEnd + " to "+ (lowEnd + size -1) + "\n";
	}


	public void setProcess(Process p) {
		lp = p;
		
	}

}