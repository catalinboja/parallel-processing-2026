package ro.ase.ie.paralel;

public class ThreadPrime2 extends Thread{
	long limitaInferioara;
	long limitaSuperioara;
	int salt;
	int contor;
	
	public ThreadPrime2(long limitaInferioara, long limitaSuperioara, int salt) {
		super();
		this.limitaInferioara = limitaInferioara;
		this.limitaSuperioara = limitaSuperioara;
		this.salt = salt;
	}

	public int getContor() {
		return contor;
	}
	
	public void run() {
		System.out.println("Thread Start");
		double tStart = System.currentTimeMillis();
		
		for(long valoare  = this.limitaInferioara; 
				valoare < this.limitaSuperioara; valoare+= this.salt) {
			if(TestNumerePrime.estePrim(valoare))
				this.contor += 1;
		}
		
		double tFinal = System.currentTimeMillis();
		System.out.println("Thread Stop");
		System.out.println(tFinal-tStart);
	}
	
	
	
}
