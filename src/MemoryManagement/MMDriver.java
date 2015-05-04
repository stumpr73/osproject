package MemoryManagement;





public class MMDriver {
	
	public static void main(String args[])
	{
		MemoryManager mm = new MemoryManager();
		Process p2 = new Process(16, "Process B");
		Process p = new Process(8, "Process A");
		Process p3 = new Process(16, "Process C");
		
		
		mm.allocate(p2);
		mm.allocate(p);
		mm.allocate(p3);
		
		
		System.out.println(mm.toString());
		
		mm.deallocate(p3);
		mm.deallocate(p2);
		//mm.deallocate(p);
		
		//mm.allocate(new Process(64, "Process D"));
		//mm.allocate(p3);
		
		System.out.println(mm.toString());
		
		System.out.println("Stop");
	}
	


}
