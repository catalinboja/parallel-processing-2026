package ro.csie.ie.paralel;

public class ThreadWorker extends Thread{
	ContorGlobal contor;
	int nrPrelucrari;
	
	public ThreadWorker(ContorGlobal contor, int nrPrelucrari) {
		super();
		this.contor = contor;
		this.nrPrelucrari = nrPrelucrari;
	}

	@Override
	public void run() {
		TestMain.proceseazaDate(contor, nrPrelucrari);
	}
	
}
