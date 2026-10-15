package ro.csie.ie.paralel;

public class TestMain {
	
	public static void proceseazaDate(
			ContorGlobal contor, int nrPrelucrari)
	{
		for(int i = 0; i < nrPrelucrari; i++) {
			for(int j = 0 ; j < 3000; j++) {
				float temp = i % 3;
				int rezultat = (int) (temp / 2);
					contor.increment();
			}
		}
	}
	
	public static void proceseazaDateAtomica(
			ContorGlobalAtomic contor, int nrPrelucrari)
	{
		for(int i = 0; i < nrPrelucrari; i++) {
			for(int j = 0 ; j < 3000; j++) {
				float temp = i % 3;
				int rezultat = (int) (temp / 2);
					contor.increment();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		ContorGlobal contor = new ContorGlobal();
		int nrPrelucrari = (int) 1e6;
		System.out.println("Start");
		
		double tStart = System.currentTimeMillis();
		proceseazaDate(contor, nrPrelucrari);
		double tFinal = System.currentTimeMillis();
		
		System.out.println("Rezultat: " + contor.contor);
		System.out.println("Durata: " + (tFinal - tStart));
		
		contor.reset();
		ContorGlobal contor2 = new ContorGlobal();
		ThreadWorker w1 = new ThreadWorker(contor, nrPrelucrari/2);
		ThreadWorker w2 = new ThreadWorker(contor2, nrPrelucrari/2);
		
		tStart = System.currentTimeMillis();
		w1.start();
		w2.start();
		
		w1.join();
		w2.join();
		
		tFinal = System.currentTimeMillis();
		System.out.println("Rezultat: " +
				(contor.contor + contor2.contor));
		System.out.println("Durata: " + (tFinal - tStart));
		
		//test variabila atomica
		ContorGlobalAtomic contorAtomic = 
				new ContorGlobalAtomic();
		tStart = System.currentTimeMillis();
		proceseazaDateAtomica(contorAtomic, nrPrelucrari);
		tFinal = System.currentTimeMillis();
		
		System.out.println("Rezultat: " + contorAtomic.contor);
		System.out.println("Durata: " + (tFinal - tStart));
		
		System.out.println("Sfarsit");
	}

}




