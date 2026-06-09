package edu.eci.arsw.samples;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Representa un hilo procesador individual que simula la ejecución de una tarea en varias iteraciones.
 * Utiliza un CountDownLatch compartido para notificar al hilo principal una vez que su tarea ha finalizado.
 */
public class HiloProc extends Thread{

	int waitPeriod=0;
	int idHilo=0;
	long resultado=0;
	private static CountDownLatch latch;
	
	/**
	 * Configura el pestillo de sincronización (CountDownLatch) compartido por todos los hilos.
	 * @param l Pestillo de cuenta regresiva.
	 */
	public static void setLatch(CountDownLatch l) {
		latch = l;
	}
	
	/**
	 * Constructor de HiloProc. Inicializa el identificador y calcula un período de espera aleatorio.
	 * @param id Identificador único del hilo.
	 */
	public HiloProc(int id){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		waitPeriod=Math.abs(new Random(System.currentTimeMillis()).nextInt()%5000);
		idHilo=id;
	}
	
	/**
	 * Método de ejecución del hilo. Realiza 10 iteraciones de simulación, calcula el tiempo total
	 * transcurrido y decrementa el pestillo de sincronización CountDownLatch.
	 */
	public void run(){
		int numit=10;
		long startTime=System.currentTimeMillis();
		for (int i=0;i<numit;i++){
			System.out.println("Soy el hilo "+idHilo+" y voy en el "+((float)((float)(i+1)/(float)numit)*100)+"% de mi tarea. P:"+waitPeriod);
			try {
				Thread.sleep(waitPeriod);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		resultado=System.currentTimeMillis()-startTime;
		latch.countDown();
	}
	
	

	/**
	 * Obtiene el tiempo total en milisegundos que tomó la ejecución del hilo.
	 * @return Tiempo de procesamiento en milisegundos.
	 */
	public long getResultado() {
		return resultado;
	}
}
