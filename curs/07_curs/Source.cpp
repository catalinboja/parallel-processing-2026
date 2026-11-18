#include <string>
#include <iostream>
#include <vector>
#include <thread>
#include <mutex>
#include <omp.h>

using namespace std;

//definire mutex global
mutex m;

void hello() {
	//std::cout << endl << "Hello de pe un thread";
	printf("\n Hello de pe un thread");
}

void adunaPeFireDiferite(int a, int b) {
	int rezultat = a + b;
	printf("\n Suma este %d", rezultat);
}

void sumaElementeVector(
	vector<int> valori,
	int& rezultat) {

	rezultat = 0;
	for (int i = 0; i < valori.size(); i++) {
		rezultat += valori[i];
	}

}

//metoda care incrementeaza un contor
void increment(int& contor) {
	for (int i = 0; i < 1e6; i++) {
		int temp = i % 3;
		m.lock();
		contor += 1;
		m.unlock();
	}
}

void increment2(mutex& m, int& contor) {
	for (int i = 0; i < 1e6; i++) {
		int temp = i % 3;
		m.lock();
		contor += 1;
		m.unlock();
	}
}

void incrementLocal(int& contor) {
	for (int i = 0; i < 1e9; i++) {
		int temp = i % 3;
		contor += 1;
	}
}




int main() {

	int a = 0;
	alignas(1024) int b = 0;

	thread t1(hello);
	thread t2(hello);

	t1.join();
	t2.join();

	thread t3(adunaPeFireDiferite, 2, 3);
	thread t4(adunaPeFireDiferite, 5, 6);

	t4.join();
	t3.join();

	vector<int> v;
	v.push_back(10);
	v.push_back(20);
	v.push_back(30);
	int rezultat = 0;

	thread tSuma(sumaElementeVector, v, ref(rezultat));

	tSuma.join();

	printf("\n Rezultat: %d", rezultat);

	//test race condition
	int contor = 0;
	thread tContor1(increment, ref(contor));
	thread tContor2(increment, ref(contor));

	tContor1.join();
	tContor2.join();

	printf("\n Contor = %d", contor);

	mutex mutexLocal;
	contor = 0;
	thread tContorMutexLocal1(
		increment2, ref(mutexLocal), ref(contor));
	thread tContorMutexLocal2(
		increment2, ref(mutexLocal), ref(contor));

	tContorMutexLocal1.join();
	tContorMutexLocal2.join();

	printf("\n Contor = %d", contor);

	//test problema cache coherence


	double tStart = omp_get_wtime();
	thread tCache1(incrementLocal, ref(a));
	thread tCache2(incrementLocal, ref(b));

	tCache1.join();
	tCache2.join();

	double tFinal = omp_get_wtime();

	printf("\n A = %d", a);
	printf("\n B = %d", b);
	printf("\n Durata = %f", (tFinal-tStart));



	return 0;
}