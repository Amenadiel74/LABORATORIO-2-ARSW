package edu.eci.arsw.samples;

import java.util.concurrent.CountDownLatch;

/**
 * Clase principal (Main) que orquesta el lanzamiento de múltiples hilos procesadores.
 * Utiliza CountDownLatch para esperar a que finalicen todos los hilos antes de calcular
 * y mostrar el tiempo promedio de ejecución.
 */
public class Main {

	/**
	 * Punto de entrada principal para el programa de sincronización por barrera.
	 * @param args Argumentos de consola (no se utilizan).
	 * @throws InterruptedException Si ocurre una interrupción mientras se espera en la barrera.
	 */
	public static void main(String[] args) throws InterruptedException {
		int numHilos=20;
		
		CountDownLatch latch = new CountDownLatch(numHilos);
		HiloProc.setLatch(latch);
		
		HiloProc[] hilos=new HiloProc[numHilos];
		
		for (int i=0;i<numHilos;i++){
			hilos[i]=new HiloProc(i);
		}
		for (int i=0;i<numHilos;i++){
			hilos[i].start();
		}

		latch.await();

		long tiempoPromedio=0;
		
		for (int i=0;i<numHilos;i++){
			tiempoPromedio+=hilos[i].getResultado();
		}

		System.out.println("El tiempo promedio de la ejecucion fue de: "+tiempoPromedio/numHilos);
	}
	
}
