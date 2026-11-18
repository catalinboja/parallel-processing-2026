package ro.ase.ie.paralel;

import java.util.Random;

public class ThreadConsumator extends Thread{
	Market market;
	String nume;
	
	
	public ThreadConsumator(Market market, String nume) {
		super();
		this.market = market;
		this.nume = nume;
	}
	
	public void run() {
		Random random = new Random();
		while(!this.market.isEsteInchisa()) {
			int valoare = random.nextInt(10);
			System.out.println("Consumatorul " + this.nume + 
					" a consumat: " + valoare);
			try {
				this.market.consuma(valoare);
				//this.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
