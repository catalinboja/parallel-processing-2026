package ro.ase.ie.paralel;

public class TestJoc {

	public static void main(String[] args) throws InterruptedException {
		
		ThreadGeneratorNumere tombola = new ThreadGeneratorNumere(50);
		ThreadJucator ana = new ThreadJucator(13, "Ana", tombola);
		ThreadJucator bob = new ThreadJucator(25, "Bob", tombola);
		ThreadJucator john = new ThreadJucator(40, "John", tombola);
		
		ana.start();
		john.start();
		bob.start();
		
		tombola.start();
		
		tombola.join();
		ana.join();
		bob.join();
		john.join();
		
		System.out.println("Sfarsit exemplu");
		
	}

}
