package ro.ase.csie.ie.paralel;

public class ThreadWorker extends Thread {

	ContorGlobal contor;
	int nrPrelucrari;
	
	public ThreadWorker(ContorGlobal contor, int nrPrelucrari) {
		this.contor = contor;
		this.nrPrelucrari = nrPrelucrari;
	}

	@Override
	public void run() {
		System.out.println("Worker start");
		for(int i = 0; i < this.nrPrelucrari; i++) {
			for(int j = 1 ; j <= 1000; j++) {
				float rezultat = 56 % j;
				float temp = rezultat + 1;
				if(j%500 == 0) {
					this.contor.increment();
					}
				}
			}
		}
}
