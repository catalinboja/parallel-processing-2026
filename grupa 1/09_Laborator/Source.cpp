#include<omp.h>
#include<iostream>
#include<mutex>
#include<vector>

int main() {

	const int N = 100000;
	int valori[N];

	int contor = 0;

	printf("\n Numar procesoare: %d", omp_get_num_procs());

	//C++
	std::mutex m;
	//OMP
	omp_lock_t mOmp;
	omp_init_lock(&mOmp);

#pragma omp parallel
	{
		int idThread = omp_get_thread_num();
		int nrThreaduri = omp_get_num_threads();
		int dimensiuneInterval = N / nrThreaduri;

		int indexStart = idThread * dimensiuneInterval;
		int indexFinal = (idThread + 1) * dimensiuneInterval;

		//printf("\n Id = %d", idThread);
		for (int i = indexStart; i < indexFinal; i++) {
			valori[i] = 1;
#pragma omp critical
			//omp_set_lock(&mOmp);
			contor += 1;
			//omp_unset_lock(&mOmp);
		}
		//printf("\n Initializare completa pentru N");
	}

	printf("\n Numar initializari: %d", contor);

	contor = 0; 
	std::vector<int> contoareLocale;
	int nrFireParalele = 0;

	omp_set_num_threads(omp_get_num_procs());
	contoareLocale.assign(omp_get_num_procs(), 0);

#pragma omp parallel
	{
		int idThread = omp_get_thread_num();
		int nrThreaduri = omp_get_num_threads();

#pragma omp master
		{
			nrFireParalele = nrThreaduri;
		}

		int dimensiuneInterval = N / nrThreaduri;

		int indexStart = idThread * dimensiuneInterval;
		int indexFinal = (idThread + 1) * dimensiuneInterval;

		for (int i = indexStart; i < indexFinal; i++) {
			valori[i] = 1;
			contoareLocale[idThread] += 1;
		}
	}

	for (int i = 0; i < nrFireParalele; i++) {
		contor += contoareLocale[i];
	}

	printf("\n Numar initializari: %d", contor);
	printf("\n Initializare completa");

}