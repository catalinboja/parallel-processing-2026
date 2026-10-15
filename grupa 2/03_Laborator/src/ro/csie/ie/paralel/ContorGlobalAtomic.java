package ro.csie.ie.paralel;

import java.util.concurrent.atomic.AtomicLong;

public class ContorGlobalAtomic {
	AtomicLong contor = new AtomicLong(0);
	
	public void increment() {
		this.contor.incrementAndGet();
	}
	
	public void reset() {
		this.contor.set(0);
	}
}
