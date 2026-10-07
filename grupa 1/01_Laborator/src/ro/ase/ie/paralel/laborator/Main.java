package ro.ase.ie.paralel.laborator;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		//secvential
		System.out.println("Hello 1");
		System.out.println("Hello 2");
		System.out.println("Hello 3");
		
		HelloThread t1 = new HelloThread("Bye 1");
		HelloThread t2= new HelloThread("Bye 2");
		HelloThread t3 = new HelloThread("Bye 3");
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		System.out.println("The end");
		
	}

}
