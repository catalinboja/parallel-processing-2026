package ro.ase.ie.paralel;

public class ThreadJucator extends Thread{
	
	int numarNorocos;
	String nume;
	Tombola tombola;
	
	public ThreadJucator(int numarNorocos, String nume, Tombola tombola) {
		super();
		this.numarNorocos = numarNorocos;
		this.nume = nume;
		this.tombola = tombola;
	}
	
	public void run() {
		while(true) {
			if(tombola.getNumar() == this.numarNorocos) {
				System.out.println("************* " + this.nume + " a castigat ");
				break;
			}
			if(tombola.getNumar() == -1) {
				break;
			}
		}
	}
	
}







