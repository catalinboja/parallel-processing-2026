package ro.ase.csie.ie.paralel;

public class ContorGlobal {
	
	int contor = 0;
	
	public synchronized void increment() {
		this.contor += 1;
	}
	
	public int getContor() {
		return this.contor;
	}
	
	public void reset() {
		this.contor = 0;
	}
	
	
}
