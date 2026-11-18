package ro.ase.ie.paralel;

public class ThreadPrime extends Thread{
	long limitaInferioara;
	long limitaSuperioara;
	int contor;
	
	public ThreadPrime(long limitaInferioara, long limitaSuperioara) {
		super();
		this.limitaInferioara = limitaInferioara;
		this.limitaSuperioara = limitaSuperioara;
	}

	public int getContor() {
		return contor;
	}
	
	public void run() {
		System.out.println("Thread Start");
		double tStart = System.currentTimeMillis();
		for(long valoare  = this.limitaInferioara; 
				valoare < this.limitaSuperioara; valoare ++) {
			if(TestNumerePrime.estePrim(valoare))
				this.contor += 1;
		}
		double tFinal = System.currentTimeMillis();
		System.out.println("Thread Stop");
		System.out.println(tFinal-tStart);
	}
	
	
	
}
