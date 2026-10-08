package ro.ase.ie.paralel;

public class ThreadAgent extends Thread{
	
	private String nume;
	private ThreadAgent target;
	
	public ThreadAgent(String nume) {
		super();
		this.nume = nume;
	}
		
	public String getNume() {
		return nume;
	}
	

	public void setTarget(ThreadAgent target) {
		this.target = target;
	}

	public void raspunde(ThreadAgent sursa) {
		System.out.println(this.nume + " --> Hello back -->" + 
				sursa.getNume());
	}
	
	public synchronized void hello(ThreadAgent target) {
		System.out.println(this.nume + " --> Hello --> " + 
				target.getNume());
		target.raspunde(this);
	}

	@Override
	public void run() {
		this.hello(target);
	}
	
	
}
