package ro.ase.csie.ie.paralel;

public class TestMain {

	public static void main(String[] args) throws InterruptedException {
		//exemplu deadlock
//		ThreadAgent executant = new ThreadAgent("Executor");
//		ThreadAgent validator = new ThreadAgent("Validator");
//		executant.setPereche(validator);
//		validator.setPereche(executant);
//		
//		executant.start();
//		validator.start();
//		
//		executant.join();
//		validator.join();
		
		
		//test secvential fara thread-uri
		ContorGlobal contor = new ContorGlobal();
		int nrPrelucrari = (int) 1e6;
		
		double tStart = System.currentTimeMillis();
		for(int i = 0; i < nrPrelucrari; i++) {
			for(int j = 1 ; j <= 1000; j++) {
				float rezultat = 56 % j;
				float temp = rezultat + 1;
				if(j%500 == 0) {
					contor.increment();
				}
			}
		}
		double tFinal = System.currentTimeMillis();
		System.out.println("Durata: " + (tFinal - tStart));
		System.out.println("Rezultat: " + contor.getContor());
		
		contor.reset();
		ThreadWorker worker = new ThreadWorker(contor, nrPrelucrari);
		tStart = System.currentTimeMillis();
		worker.start();
		worker.join();
		tFinal = System.currentTimeMillis();
		
		System.out.println("Durata: " + (tFinal - tStart));
		System.out.println("Rezultat: " + contor.getContor());
		
		contor.reset();
		ThreadWorker w1 = new ThreadWorker(contor, nrPrelucrari/2);
		ThreadWorker w2 = new ThreadWorker(contor, nrPrelucrari/2);
		tStart = System.currentTimeMillis();
		w1.start();
		w2.start();
		w1.join();
		w2.join();
		tFinal = System.currentTimeMillis();
		System.out.println("Durata: " + (tFinal - tStart));
		System.out.println("Rezultat: " + contor.getContor());
		
		
		System.out.println("Sfarsit main thread");
		
	}

}
