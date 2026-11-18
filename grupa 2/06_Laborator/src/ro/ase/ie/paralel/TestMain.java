package ro.ase.ie.paralel;

public class TestMain {

	public static void main(String[] args) throws InterruptedException {
		
		Market market = new Market(1000);
		ThreadProducator p1 = new ThreadProducator(market, "P1");
		ThreadProducator p2 = new ThreadProducator(market, "P2");
		
		ThreadConsumator c1 = new ThreadConsumator(market, "C1");
		ThreadConsumator c2 = new ThreadConsumator(market, "C2");
		
		p1.start();
		p2.start();
		
		Thread.sleep(10);
		
		c1.start();
		c2.start();
		
		Thread.sleep(15000);
		market.inchide();
		
		p1.join();
		p2.join();
		c1.join();
		c2.join();
		
		System.out.println("Market closed");
		
	}

}






