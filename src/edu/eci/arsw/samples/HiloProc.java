package edu.eci.arsw.samples;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class HiloProc extends Thread{

	int waitPeriod=0;
	int idHilo=0;
	long resultado=0;
	private static CountDownLatch latch;
	
	public static void setLatch(CountDownLatch l) {
		latch = l;
	}
	
	public HiloProc(int id){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		waitPeriod=Math.abs(new Random(System.currentTimeMillis()).nextInt()%5000);
		idHilo=id;
	}
	
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
	
	

	public long getResultado() {
		return resultado;
	}
}
