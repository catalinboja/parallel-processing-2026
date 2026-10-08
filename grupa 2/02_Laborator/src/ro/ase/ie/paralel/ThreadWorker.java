package ro.ase.ie.paralel;

public class ThreadWorker extends Thread{

	Contor contor;
	int limitaInferioara;
	int limitaSuperioara;
	
	public ThreadWorker(Contor contor, int limitaInferioara, int limitaSuperioara) {
		this.contor = contor;
		this.limitaInferioara = limitaInferioara;
		this.limitaSuperioara = limitaSuperioara;
	}

	@Override
	public void run() {
		double tStart = System.currentTimeMillis();	
		for(int i = limitaInferioara; i <= limitaSuperioara; i++) {
			for(int j = 0; j < 1000000; j++) {
				float rezultat = j % 3;
				contor.increment();
			}
			
		}
		double tFinal = System.currentTimeMillis();
		System.out.println("Thread terminat in " + (tFinal- tStart));
	}
	
}
