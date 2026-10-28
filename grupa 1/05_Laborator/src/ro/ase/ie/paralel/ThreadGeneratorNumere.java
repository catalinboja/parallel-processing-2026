package ro.ase.ie.paralel;

import java.util.Random;

public class ThreadGeneratorNumere extends Thread{

	volatile int numarExtras;
	int numarExtrageri;
	
	public void run() {
		int extrageri = 0;
		Random random = new Random();
		while(extrageri++ < this.numarExtrageri) {
			this.numarExtras = (random.nextInt(50) + 1); //de la 1 .. 50
			System.out.println("E fost extras numarul: " + this.numarExtras);
			
			try {
				this.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//poison pill
		this.numarExtras = -1;
		
		System.out.println("Sfarsit JOC");
	}

	public int getNumarExtras() {
		return numarExtras;
	}

	public ThreadGeneratorNumere(int numarExtrageri) {
		super();
		this.numarExtrageri = numarExtrageri;
	}
	
}






