package ro.ase.ie.paralel;

public class TestMain {
	
	public static void procesareDate(ContorAtomic contor, int nrOperatii) {
		for(int i = 0; i < nrOperatii; i++) {
			for(int j = 0; j < 10000; j++) {
				int temp = j % 33;
				int rezultat = temp + 2;
				contor.increment();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int nrOperatii = (int)3e5;
		ContorAtomic contor = new ContorAtomic();
		
		double tStart = System.currentTimeMillis();
		procesareDate(contor, nrOperatii);
		double tFinal = System.currentTimeMillis();
		
		System.out.println("Rezultat: " + contor.get());
		System.out.println("Durata: " + (tFinal - tStart));
		
		ContorAtomic contor1 = new ContorAtomic();
		ContorAtomic contor2 = new ContorAtomic();
		
		Thread t1 = new Thread() {
			public void run() {
				procesareDate(contor1, nrOperatii/2);
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				procesareDate(contor2, nrOperatii/2);
			}
		};
		
		tStart = System.currentTimeMillis();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		tFinal = System.currentTimeMillis();
		
		System.out.println("Rezultat: " + (contor1.get() + contor2.get()));
		System.out.println("Durata: " + (tFinal - tStart));
		

	}

}
