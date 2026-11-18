package ro.ase.ie.paralel;

public class Market {
	int stoc = 0;
	int limitaStoc;
	
	volatile boolean esteInchisa = false;
	
	
	
	public boolean isEsteInchisa() {
		return esteInchisa;
	}
	
	public void inchide() {
		this.esteInchisa = true;
	}

	public Market(int limitaStoc) {
		super();
		this.limitaStoc = limitaStoc;
	}
	
	public synchronized void alimenteaza(int valoare) throws InterruptedException {
		
		if(this.stoc + valoare < this.limitaStoc)
		{
			this.stoc += valoare;
			System.out.println("Piata a fost alimentata cu " + valoare);
			System.out.println("Stoc disponibil: " + this.stoc);
			this.notifyAll();
		}
		else {
			wait();
		}
		
	}
	
	public synchronized void consuma(int valoare) throws InterruptedException {
		if(this.stoc - valoare >= 0) {
			this.stoc -= valoare;
			System.out.println("A fost consumata valoare " + valoare);
			System.out.println("Stoc disponibil: " +  this.stoc);
			this.notifyAll();
		}
		else {
			wait();
		}
	}
	
	
}



