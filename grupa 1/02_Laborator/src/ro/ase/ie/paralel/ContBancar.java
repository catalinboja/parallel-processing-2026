package ro.ase.ie.paralel;

public class ContBancar {
	double sold;

	public ContBancar(double sold) {
		super();
		this.sold = sold;
	}

	public double getSold() {
		return sold;
	}
	
	public synchronized void plata(double valoare) {
		System.out.println("Verificare plata cu valoare: " + valoare);
		if(valoare <= this.sold) {
			System.out.println("Plata efectuata: " + valoare);
			this.sold -= valoare;
			System.out.println("Sold disponibil: " + this.sold);
		}
		else {
			System.out.println("Plata refuzata");
		}
	}
}
