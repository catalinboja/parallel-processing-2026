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
		
		System.out.println(msg);
		
		double tFinal = System.currentTimeMillis();
		System.out.println("Durata thread: " + (tFinal - tStart));
	}

}
