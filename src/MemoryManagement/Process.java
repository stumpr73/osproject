package MemoryManagement;

public class Process {
	private int size;
	private String name;
	public Process(int s, String n){
		size = s;
		name = n;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
