package ro.ase.ie.paralel;

public class ThreadNumerePrime extends Thread{
	int contor;
	long limitaInferioara;
	long limitaSuperioara;
	
	public ThreadNumerePrime(long limitaInferioara, long limitaSuperioara) {
		super();
		this.limitaInferioara = limitaInferioara;
		this.limitaSuperioara = limitaSuperioara;
	}

	public int getContor() {
		return contor;
	}
	
	public void run() {
		System.out.println("Start thread");
		double tStart = System.currentTimeMillis();
		for(long i = limitaInferioara; i < limitaSuperioara; i++) {
			boolean estePrim = true;
			for(long j = 2; j <= (int)Math.sqrt(i); j++) {
				if(i % j == 0) {
					estePrim = false;
					break;
				}
			}
			if(estePrim)
			{
				this.contor+=1;
				//System.out.println(i);
			}
		}
		double tFinal = System.currentTimeMillis();
		System.out.println("Stop thread in " + (tFinal - tStart));
	}
	
	
	
}



