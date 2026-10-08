package ro.ase.ie.paralel;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		//test deadlock
		ThreadAgent t1 = new ThreadAgent("Agent 1");
		ThreadAgent t2 = new ThreadAgent("Agent 2");
		t1.setTarget(t2);
		t2.setTarget(t1);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		//test cost sync
		Contor contor = new Contor();
//		ThreadWorker w1 = new ThreadWorker(contor, 1, (int) 1e4);
//		w1.start();
//		w1.join();
//		System.out.println("Contor: " + contor.getContor());
//
//		contor.reset();
		
		ThreadWorker w2 = new ThreadWorker(contor, 1, (int) 1e4/2);
		ThreadWorker w3 = new ThreadWorker(contor, (int) 1e4/2 + 1,
				(int) 1e4);
		
		w2.start();
		w3.start();
		
		w2.join();
		w3.join();
		
		System.out.println("Contor: " + contor.getContor());
		
		
	}

}
