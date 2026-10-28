package ro.ase.ie.paralel;

public class Test {

	public static void main(String[] args) throws InterruptedException {

		Tombola tombola = new Tombola();
		
		ThreadJucator alice = new ThreadJucator(23, "Alice", tombola);
		ThreadJucator john = new ThreadJucator(30, "John", tombola);
		ThreadJucator bob = new ThreadJucator(13, "Bob", tombola);
		
		alice.start();
		john.start();
		bob.start();
		
		tombola.start();
		
		tombola.join();
		alice.join();
		john.join();
		bob.join();
		
		System.out.println("Sfarsit joc");
		
	}

}
