package ro.ase.ie.paralel;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		ContBancar cont = new ContBancar(1000);
		ThreadAgent t1 = new ThreadAgent(cont);
		ThreadAgent t2 = new ThreadAgent(cont);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("The end");
	}

}
