package MemoryManagement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.GridLayout; //imports GridLayout library


public class GUI {

	private JFrame frame=new JFrame(); 
	private JTextArea[][] grid; 
	private int width;
	private int length;
	private Tree t;

	public GUI(int w, int l, Tree tree){
		width = w;
		length = l;
		t = tree;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Tree getT() {
		return t;
	}
	public void setT(Tree t) {
		this.t = t;
	}

	public void makeGrid(){ //constructor
		frame.setLayout(new GridLayout(width,length)); //set layout
		grid=new JTextArea[width][length];
		Border blackline = BorderFactory.createLineBorder(Color.black);
		for(int y=0; y<length; y++){
			for(int x=0; x<width; x++){
				grid[x][y]=new JTextArea(""+y);  
				frame.add(grid[x][y]);
				grid[x][y].setBorder(blackline);
				if(!(grid[x][y].getBackground().equals(Color.green))){
					if(t.getLp()!= null){
						for(int i = y; i<t.getSize();i++){
							grid[i][y].setBackground(Color.green);
						}
					}
				}
			}
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); 
		frame.setVisible(true);
	}

}
