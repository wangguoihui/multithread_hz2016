package com.cn.hangzhou.hole;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 过独木桥问题
 * @author Administrator
 *
 */
public class SemaphoreHole {
	
	 	private static final int       THREAD_COUNT = 10;

	    private static ExecutorService threadPool   = Executors.newFixedThreadPool(THREAD_COUNT);

	    static Semaphore semaphore = new Semaphore(2);
	    
	    public static void main(String[] args) {
	        for (int i = 0; i < THREAD_COUNT; i++) {
	            threadPool.execute(new Runnable() {
	                @Override
	                public void run() {
	                    try {
	                    	semaphore.acquire();
	                        System.out.println(Thread.currentThread().getName());
	                        TimeUnit.SECONDS.sleep(2);
	                    	semaphore.release();
	                    } catch (InterruptedException e) {
	                    }
	                }
	            });
	        }

	        threadPool.shutdown();
	    }
	
}
