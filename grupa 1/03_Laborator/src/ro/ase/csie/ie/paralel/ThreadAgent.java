package ro.ase.csie.ie.paralel;

public class ThreadAgent extends Thread {
	
	ThreadAgent pereche;
	String nume;
	
	public ThreadAgent(String nume) {
		super();
		this.nume = nume;
	}
	
	public String getNume() {
		return nume;
	}

	public void setPereche(ThreadAgent pereche) {
		this.pereche = pereche;
	}
	
	//deadlock 
	public synchronized void ack() {
		System.out.println(this.nume + " -> ack -> " + pereche.getNume());
	}

	//deadlock 
	public synchronized void hello() {
		System.out.println(this.nume + " -> hello -> " + pereche.getNume());
		this.pereche.ack();
	}

	@Override
	public void run() {
		this.hello();
	}
	
}
