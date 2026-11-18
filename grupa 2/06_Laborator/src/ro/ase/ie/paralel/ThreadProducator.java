package ro.ase.ie.paralel;

import java.util.Random;

public class ThreadProducator extends Thread{
	Market market;
	String nume;
	
	
	public ThreadProducator(Market market, String nume) {
		super();
		this.market = market;
		this.nume = nume;
	}
	
	public void run() {
		Random random = new Random();
		while(!this.market.isEsteInchisa()) {
			int valoare = random.nextInt(10);
			System.out.println("Producatorul " + this.nume + 
					" a produs: " + valoare);
			try {
				this.market.alimenteaza(valoare);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
