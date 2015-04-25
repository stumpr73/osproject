package MemoryManagement;

/**
 * This is a tree node.  It can have either 2 child nodes or none.  It has a size
 * which is an integer that corresponds to the amount of memory.  The size left field
 * is used to describe how much memory is left in the child nodes below it.
 * 
 * @author Ryan Stump, John Masterson, David Ferrara, Justin Gulutz
 *
 */
public class Tree {
	private Tree left;
	private Tree right;
	private int size;
	private int sizeLeft; //the available space underneath this node
	private Process lp;
	private Tree parent;

	/**
	 * Constructor
	 * @param s -the size of the block of memory
	 * @param p -the parent of the node
	 */
	public Tree(int s, Tree p){
		left = null;
		right = null;
		size = s;
		sizeLeft = size;
		lp = null;
		parent = p;
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
	
	public int getSizeLeft() {
		return sizeLeft;
	}
	
	public void setSizeLeft(int sizeLeft) {
		this.sizeLeft = sizeLeft;
	}
	
	public Process getLp() {
		return lp;
	}
	
	public void setLp(Process lp) {
		this.lp = lp;
	}
	
	public Tree getParent(){
		return parent;
	}
	
	public void setParent(Tree parent) {
		this.parent = parent;
	}

	/**
	 * Allocates a place where the process p can be put
	 * @param p -the process trying to be placed in memory
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
	 * Generates a left child and right child with the
	 * half of the memory
	 */
	public void generateChildren(){
		left = new Tree(size/2,this);
		right = new Tree(size/2,this);
	}

	/**
	 * Determines if the left or right child can fit the process in it
	 * @param p -process
	 * @return -the node where the process can fit
	 */
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

	/**
	 * Assigns the process p to this node.
	 * @param p -process
	 */
	public void assignProcess(Process p){
		int adjustedSize = (int)Math.pow(2,Math.ceil(Math.log(p.getSize())/Math.log(2)));
		lp = p;

		updateParentSize(adjustedSize);

		System.out.println("The " + p.getName() + " was assigned to a node with size " + size);
	}

	/**
	 * When a node either gets allocated, that node's parents need to
	 * @param s -the size of the process that was just assigned
	 */
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

	/**
	 * Deallocates the process p from memory
	 * @param p -process
	 */
	public void deallocate(Process p){
		Tree n = find(p);
		manageDeallocation(n);
		n.updateParentSize((n.getSize()*-1));
		System.out.println("The " + p.getName() + " was deallocated");
	}

	/**
	 * If two buddy processes don't have process in them anymore
	 * they get deleted
	 * @param n -the node where the process was located
	 */
	public void manageDeallocation(Tree n)
	{
		if(n.parent.getLeft().getLp() == null || n.parent.getRight().getLp() == null)
		{
			n.parent.resetChildren();
		}
		else if(n.parent.getLeft().getLp() != null || n.parent.getRight().getLp() != null)
			n.setLp(null);
		else
			manageDeallocation(n.parent);
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
	 * Finds the node where this process is located
	 * @param p
	 * @return the node where the process is located or null if it was not
	 * found
	 */
	public Tree find(Process p){

		if(areNoChildren() && lp != null)
		{	
			if(lp.equals(p))
				return this;
			else
				return null;
		}

		else if(areNoChildren() && lp == null)
			return null;
		else{
			Tree tree1 = right.find(p);
			Tree tree2 = left.find(p);
			if(tree1 != null)
			{
				return tree1;	
			}
			else if (tree2 != null)
			{
				return tree2;
			}
			else
				//at this point neither of this node's children have the process
				return null;
		}
	}

	public boolean areNoChildren()
	{
		return left == null;
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
}
