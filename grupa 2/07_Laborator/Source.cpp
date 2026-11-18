#include <iostream>
#include <string>
#include <vector>
#include <thread>
#include <omp.h>
#include <mutex>

using namespace std;

//mutex global
mutex mutexGlobal;

void hello() {
	cout << endl << "Hello " << " - " << " de pe thread";
}

void aduna(int a, int b) {
	int rezultat = a + b;
	printf("\n Suma rezultata este %d", rezultat);
}

void adunaCuRezultat(int a, int b, int& rezultat) {
	rezultat = a + b;
	printf("\n Calcul suma");
}

void contorUniversal(int nrIteratii, int& contor) {
	for (int i = 0; i < nrIteratii; i++) {
		int temp = i % 3;
		contor += 1;
	}
}

void contorConcurent(int nrIteratii, int& contor) {
	for (int i = 0; i < nrIteratii; i++) {
		mutexGlobal.lock();
		contor += 1;
		mutexGlobal.unlock();
	}
}

void contorConcurentCuMutex(
	int nrIteratii, int& contor, mutex& m) {
	for (int i = 0; i < nrIteratii; i++) {
		m.lock();
		contor += 1;
		m.unlock();
	}
}


int main() {

	// hello pe mai multe thread-uri
	thread t1(hello);
	thread t2(hello);

	t1.join();
	t2.join();

	//suma pe 2 thread-uri
	thread tSuma1(aduna, 2, 3);
	thread tSuma2(aduna, 5, 7);

	tSuma1.join();
	tSuma2.join();

	int rezultat = 0;
	thread tSumaRezultat(
		adunaCuRezultat, 3, 100, ref(rezultat));


	tSumaRezultat.join();
	printf("\n Rezultat suma: %d", rezultat);

	//cache coherence problem
	//int contoare[2];
	//contoare[0] = 0;
	//contoare[1] = 0;

	int ITERATII = 1e6;
	int contoare[1000];
	contoare[0] = 0;
	contoare[999] = 0;

	alignas(1024) int vb = 0;

	double tStart = omp_get_wtime();

	thread tContor1(contorUniversal, ITERATII, ref(contoare[0]));
	thread tContor2(contorUniversal, ITERATII, ref(contoare[999]));

	tContor1.join();
	tContor2.join();

	double tFinal = omp_get_wtime();

	printf("\n Contor 1 = %d", contoare[0]);
	printf("\n Contor 2 = %d", contoare[999]);
	printf("\n Durata: %f", tFinal - tStart);

	//test race condition
	int contor = 0;

	thread tConcurent1(contorConcurent, ITERATII, ref(contor));
	thread tConcurent2(contorConcurent, ITERATII, ref(contor));

	tConcurent1.join();
	tConcurent2.join();

	printf("\n Contor: %d", contor);

	//mutex local
	mutex mutexLocal;
	contor = 0;
	thread tC1(
		contorConcurentCuMutex,
		ITERATII,
		ref(contor),
		ref(mutexLocal));

	thread tC2(
		contorConcurentCuMutex,
		ITERATII,
		ref(contor),
		ref(mutexLocal));

	tC1.join();
	tC2.join();

	printf("\n Contor: %d", contor);

	//vector de thread-uri
	int nrFire = 4;
	vector<thread> fire(nrFire);

	for (int i = 0; i < nrFire; i++) {
		fire[i] = thread(hello);
	}

	for (int i = 0; i < nrFire; i++) {
		fire[i].join();
	}


	return 0;
}