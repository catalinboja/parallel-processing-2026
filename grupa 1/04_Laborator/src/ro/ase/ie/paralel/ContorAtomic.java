package ro.ase.ie.paralel;

import java.util.concurrent.atomic.AtomicLong;

public class ContorAtomic {
	
	AtomicLong contor = new AtomicLong();
	long altContor = 0;
	
	public void increment() {
		this.contor.incrementAndGet();
		//this.altContor += 1;
	}
	
	public void reset() {
		this.contor.set(0);
	}
	
	public long get() {
		return this.contor.get();
		//return this.altContor;
	}
}
