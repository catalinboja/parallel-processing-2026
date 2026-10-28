package ro.ase.ie.paralel;

public class ThreadJucator extends Thread{
	
	int numarNorocos;
	String nume;
	ThreadGeneratorNumere generator;
	
	public ThreadJucator(int numarNorocos, String nume, ThreadGeneratorNumere generator) {
		super();
		this.numarNorocos = numarNorocos;
		this.nume = nume;
		this.generator = generator;
	}
	
	public void run() {
		while(true) {
			if(generator.getNumarExtras() == this.numarNorocos) {
				System.out.println("************* " + this.nume + 
						" a castigat cu numarul " + this.numarNorocos);
				break;
			}
			
			if(generator.getNumarExtras() == -1) {
				break;
			}
		}
	}
	
	
}
