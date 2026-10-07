package ro.ase.ie.paralel.laborator;

public class HelloThread extends Thread {
	
	private String msg;
	
	public HelloThread(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public void run() {
	
			double tStart = System.currentTimeMillis();
			
			System.out.println("Thread: " + msg);
			for(int i = 0; i < 100000000; i++) {
				float temp = i / 3;
				float temp2 = temp + 1;
			}
			
			double tFinal = System.currentTimeMillis();
			
			System.out.println("Durata thread: " + (tFinal-tStart));
		
	}
	

}
