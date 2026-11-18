package ro.ase.ie.paralel;

import java.util.ArrayList;

public class TestNumerePrime {
	
	public static boolean estePrim(long valoare) {
		boolean esteNumarPrim = true;
		for(long i = 2; i < Math.sqrt(valoare); i++) {
			if(valoare % i == 0)
			{
				esteNumarPrim = false;
				break;
			}
		}
		return esteNumarPrim;
	}
	

	public static void main(String[] args) throws InterruptedException {
		
		
		//benchmark pe 1 thread
		long maxim = (long) 1e7;
		ThreadPrime t = new ThreadPrime(1, maxim);
		
		double tStart = System.currentTimeMillis();
		t.start();
		t.join();
		double tFinal = System.currentTimeMillis();
		
		System.out.println("Nr prime: " + t.getContor());
		System.out.println("Durata: " + (tFinal - tStart));
		
		//benchmark pe 4 thread-uri
		int nrFire = 4;
		int interval = (int) (maxim / nrFire);
		ArrayList<ThreadPrime> fire = new ArrayList<>();
		
		for(int i = 0; i < nrFire; i++) {
			long limitaI = i*interval + 1;
			long limitaS = (i + 1) * interval;
			if(i == nrFire - 1)
				limitaS = maxim;
			ThreadPrime tp = new ThreadPrime(limitaI, limitaS);
			fire.add(tp);
		}
		
		tStart = System.currentTimeMillis();
		for(ThreadPrime tp : fire) {
			tp.start();
		}
		for(ThreadPrime tp : fire) {
			tp.join();
		}
		tFinal = System.currentTimeMillis();
		
		int rezultat = 0;
		for(ThreadPrime tp : fire) {
			rezultat += tp.getContor();
		}
		
		System.out.println("Nr prime: " + rezultat);
		System.out.println("Durata: " + (tFinal - tStart));
		
		//benchmark pe 5 thread-uri optimizate
		
		ArrayList<ThreadPrime2> fire2 = new ArrayList<>();
		int nrFire2 = 5;
		
		for(int i = 0; i < nrFire2; i++) {
			ThreadPrime2 tp = new ThreadPrime2(i+1, maxim, nrFire2);
			fire2.add(tp);
		}
		
		tStart = System.currentTimeMillis();
		for(ThreadPrime2 tp : fire2) {
			tp.start();
		}
		for(ThreadPrime2 tp : fire2) {
			tp.join();
		}
		tFinal = System.currentTimeMillis();
		
		rezultat = 0;
		for(ThreadPrime2 tp : fire2) {
			rezultat += tp.getContor();
		}
		
		System.out.println("Nr prime: " + rezultat);
		System.out.println("Durata: " + (tFinal - tStart));
		
		//benchmark pe 4 thread-uri care evita numere pare
		
		ArrayList<ThreadPrime3> fire3 = new ArrayList<>();
		
		for(int i = 0; i < nrFire; i++) {
			ThreadPrime3 tp = new ThreadPrime3(i*2+1, maxim, nrFire);
			fire3.add(tp);
		}
		
		tStart = System.currentTimeMillis();
		for(ThreadPrime3 tp : fire3) {
			tp.start();
		}
		for(ThreadPrime3 tp : fire3) {
			tp.join();
		}
		tFinal = System.currentTimeMillis();
		
		rezultat = 0;
		for(ThreadPrime3 tp : fire3) {
			rezultat += tp.getContor();
		}
		
		System.out.println("Nr prime: " + rezultat);
		System.out.println("Durata: " + (tFinal - tStart));
	}

}







