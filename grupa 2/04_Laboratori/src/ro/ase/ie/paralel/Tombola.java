package ro.ase.ie.paralel;

import java.util.Random;

public class Tombola extends Thread{
	
	volatile int numarExtras;
	
	int getNumar() {
		return this.numarExtras;
	}
	
	public void run() {
		int nrExtrageri = 0;
		while(true) {
			Random random = new Random();
			numarExtras = (random.nextInt(49) + 1);
			try {
				this.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Numar extras: " + this.numarExtras);
			if(++nrExtrageri > 1000)
				break;
		}
		//poison pill
		this.numarExtras = -1;
	}
}






