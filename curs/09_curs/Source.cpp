#include <iostream>
#include <vector>
#include <omp.h>

int main() {


	int contor = 0;
	int contorPartial = 10;
	int variabilaLocala = 0;
	int ultimaValoareParalela = 0;
	
	//OMP mutex
	omp_lock_t mutexOMP;
	omp_init_lock(&mutexOMP);

#pragma omp parallel shared(contor)  private(variabilaLocala)\
	firstprivate(contorPartial) 
	{
		printf("\n Hello OMP !");

		//variabila i este privata - fiecare thread are propriul i
		for (int i = 0; i < 1000000; i++) {
			//omp_set_lock(&mutexOMP);
#pragma omp critical
			{
				contor += 1;
			}
			//omp_unset_lock(&mutexOMP);
			contorPartial += 1;
		}
		printf("\n Contor partial: %d", contorPartial);

		ultimaValoareParalela = omp_get_thread_num();
	}

	printf("\n Contor: %d", contor);
	printf("\n Ultimul thread care a terminat: %d", 
		ultimaValoareParalela);


	printf("\n ---------------------------");
#pragma omp parallel num_threads(4)
	{
		//prelucrari #1
		printf("\n Secventa #1 prelucrari - T%d",
			omp_get_thread_num());
#pragma omp barrier
		//prelucrari #2
		printf("\n Secventa #2 prelucrari - T%d",
			omp_get_thread_num());

	}

	printf("\n ---------------------------");


	int indexIteratie = 0;
#pragma omp parallel num_threads(4) 
	{
#pragma omp for lastprivate(indexIteratie)
		for (indexIteratie = 0; indexIteratie < 10; indexIteratie++) {
			printf("\n Iteratia %d executata de T %d",
				indexIteratie, omp_get_thread_num());
			//in zona paralela am executie necontrolata
			//se executa iteratii cu index > 2
			if (indexIteratie > 2)
				break;
		}

		printf("\n Bye");
	}

	printf("\n Ultimul index: %d", 
		indexIteratie);


}