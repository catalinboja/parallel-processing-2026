package ro.csie.ie.paralel;

public class ContorGlobal {
	long contor = 0;
	
	public void increment() {
		this.contor += 1;
	}
	
	public void reset() {
		this.contor = 0;
	}
}
