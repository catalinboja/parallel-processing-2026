package ro.ase.ie.paralel;

import java.util.ArrayList;

public class TestMain {

	public static void main(String[] args) throws InterruptedException {
		long limitaSuperioara = (long) 1e7;
		
		//benchmark pe 1 thread
		
		ThreadNumerePrime t = new ThreadNumerePrime(1, limitaSuperioara);
		double tStart = System.currentTimeMillis();
		t.start();
		t.join();
		double tFinal = System.currentTimeMillis();
		System.out.println("Numere prime: " + t.getContor());
		System.out.println("Durata: " + (tFinal-tStart));
		
		//benchmark pe 4 thread-uri
		int nrFire = 4;
		int delta = (int) (limitaSuperioara / nrFire);
		ArrayList<ThreadNumerePrime> listaFire = new ArrayList<>();
		for(int i = 0; i < nrFire; i++) {
			long limitaI = i * delta + 1;
			long limitaS = (i+1) * delta;
			ThreadNumerePrime fir = new ThreadNumerePrime(limitaI, limitaS);
			listaFire.add(fir);
		}
		
		tStart = System.currentTimeMillis();
		for(ThreadNumerePrime tFir : listaFire) {
			tFir.start();
		}
		for(ThreadNumerePrime tFir : listaFire) {
			tFir.join();
		}
		tFinal = System.currentTimeMillis();
		
		int rezultat = 0;
		for(ThreadNumerePrime tFir : listaFire) {
			rezultat += tFir.getContor();
		}
		
		System.out.println("Numere prime: " + rezultat);
		System.out.println("Durata: " + (tFinal-tStart));
		
		
		//benchmark pe fire v2
		ArrayList<ThreadNumerePrime2> listaFire2 = 
				new ArrayList<>();
		for(int i = 0; i < nrFire; i++) {
			listaFire2.add(new ThreadNumerePrime2(
					2*i+1, limitaSuperioara, nrFire*2));
		}
		
		tStart = System.currentTimeMillis();
		for(ThreadNumerePrime2 tFir : listaFire2) {
			tFir.start();
		}
		for(ThreadNumerePrime2 tFir : listaFire2) {
			tFir.join();
		}
		tFinal = System.currentTimeMillis();
		
		rezultat = 0;
		for(ThreadNumerePrime2 tFir : listaFire2) {
			rezultat += tFir.getContor();
		}
		
		System.out.println("Numere prime: " + rezultat);
		System.out.println("Durata: " + (tFinal-tStart));
		
	}

}



