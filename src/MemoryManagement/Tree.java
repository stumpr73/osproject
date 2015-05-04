package MemoryManagement;

import java.util.ArrayList;

/**
 * This class does the majority of the work in our project.
 * The grunt work of allocating a space in memory for a new process
 * is in this class.  Efficient ways to traverse through this tree
 * are used.
 */
public class Tree {

	private Tree left; // left child of this node
	private Tree right; // right child of this node
	private int size; // size of the node
	private int sizeLeft; //the available space underneath this node
	private Process lp; // the local process of this node
	private Tree parent; // the parent of this node
	private int lowEnd; // the number in memory where this node begins
	private ArrayList<Tree> leaves; 


	/**
	 * Constructor
	 * 
	 * @param s Size of the node
	 * @param p  Parent of the node
	 * @param lowEnd The number in memory where this node will begin
	 * @param leaves  
	 */
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

	/**
	 * This method allocates a place in memory for the process P.
	 * If the size of the process is larger than the amount of
	 * memory left, an error message will be displayed.
	 * 
	 * @param p The process being placed in memory
	 */
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

	/**
	 * Generates the children of this node
	 */
	private void generateChildren(){
		leaves.remove(this);
		left = new Tree(size/2,this, lowEnd, leaves);
		right = new Tree(size/2,this, (lowEnd +(size/2)), leaves);
	}

	/**
	 * Finds a suitable node that a process can be placed
	 * @param p The process trying to be placed in memory
	 * @return  Either the left child or right child
	 */
	private Tree findSuitableChild(Process p){
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

	/**
	 * @return The sibling of the node
	 */
	public Tree getSibling()
	{
		if(parent.getLeft().equals(this))
			return parent.getRight();
		else if(parent.getRight().equals(this))
			return parent.getLeft();
		else
			return null;

	}

	/**
	 * @return The amount of memory underneath this node that is not taken up
	 */
	public int getSizeLeft(){
		return sizeLeft;
	}

	/**
	 * Assigns the process to this node
	 * @param p
	 */
	private void assignProcess(Process p){
		int adjustedSize = (int)Math.pow(2,Math.ceil(Math.log(p.getSize())/Math.log(2)));
		lp = p;
		updateParentSize(adjustedSize);
	}

	/**
	 * When a process is either added or removed in memory
	 * The size left underneath this parent needs to be updated
	 * @param s The size of the process that was either removed
	 * or added to memory
	 */
	private void updateParentSize(int s)
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

	/**
	 * @return Where this node begins in memory
	 */
	public int getLowEnd()
	{
		return lowEnd;
	}


	/**
	 * Resets the children of this node
	 */
	public void resetChildren()
	{
		left = null;
		right = null;

	}

	/**
	 * 
	 * @return  If this node has a parent
	 */
	public boolean hasParent()
	{
		if(parent == null)
			return false;
		return true;
	}

	/**
	 * Retruns the parent
	 * @return
	 */
	public Tree getParent()
	{
		return parent;
	}

	/**
	 * 
	 * @return If there are no children
	 */
	public boolean areNoChildren()
	{
		return left == null;
	}

	/**
	 * 
	 * @return The left child
	 */
	public Tree getLeft() {
		return left;
	}

	/**
	 * Sets the left child
	 * @param left
	 */
	public void setLeft(Tree left) {
		this.left = left;
	}

	/**
	 * 
	 * @return the right child
	 */
	public Tree getRight() {
		return right;
	}
	/**
	 * Sets the right child
	 * @param right
	 */
	public void setRight(Tree right) {
		this.right = right;
	}
	/**
	 * 
	 * @return The size of this node
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Sets the size of this node
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * Returns the process occupying this node
	 * @return
	 */
	public Process getLp() {
		return lp;
	}
	/**
	 * Sets the process occupying this node
	 * @param lp
	 */
	public void setLp(Process lp) {
		this.lp = lp;
	}
	/**
	 * Sets the parent occupying this node
	 * @param parent
	 */
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


}