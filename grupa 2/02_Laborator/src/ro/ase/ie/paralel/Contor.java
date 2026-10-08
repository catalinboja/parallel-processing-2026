package ro.ase.ie.paralel;

public class Contor {
	
	int contor = 0;
	
	public void increment() {
		this.contor +=1;
	}

	public int getContor() {
		return contor;
	}
	
	public void reset() {
		this.contor = 0;
	}
}
