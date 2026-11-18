#include <iostream>
#include <vector>
#include <thread>
#include <omp.h>

using namespace std;

void hello(int i) {
	printf("\n Hello de pe thread %d", i);
}

int main() {
	vector<thread> fire;
	int nrFire = 4;
	for (int i = 0; i < nrFire; i++) {
		fire.push_back(thread(hello, i + 1));
	}
	printf("\nHello de pe main thread");
	for (auto& t : fire) {
		t.join();
	}
	printf("\n Final");

	//varianta OMP
	int nrProcesoare = omp_get_num_procs();
	printf("\n Numar procesoare: %d", nrProcesoare);

	omp_set_num_threads(4);

#pragma omp parallel 
	{
		int idThread = omp_get_thread_num();

		//if (idThread == 0) 
#pragma omp master
		{
			int nrFire = omp_get_num_threads();
			printf("\n Numar thread-uri = %d", nrFire);

			int nrMaximFire = omp_get_max_threads();
			printf("\n Mr maxim fire= %d", nrMaximFire);


			if (omp_in_parallel()) {
				printf("\n Executie paralela");
			}
			else {
				printf("\n Executie secventiala");
			}
		}


		//printf("\n Hello de pe thread %d", idThread);
	}

	omp_set_num_threads(6);
	bool doParallel = 
		omp_get_num_procs() > 6 ? true : false;
	int nrFireZonaCritica = 8;

#pragma omp parallel if(doParallel) \
	num_threads(nrFireZonaCritica)
	{
		printf("\nBye !");
	}

}