package ro.ase.ie.paralel;

import java.util.Random;

public class ThreadAgent extends Thread{
	ContBancar cont;

	public ThreadAgent(ContBancar cont) {
		this.cont = cont;
	}

	@Override
	public void run() {
		while(true) {
			Random random = new Random();
			int valoare = random.nextInt(10);
			System.out.println("Agentul incearca plata de " + valoare);
			this.cont.plata(valoare);
			
			if(this.cont.getSold() <= 0) {
				break;
			}
		}
	}
}




