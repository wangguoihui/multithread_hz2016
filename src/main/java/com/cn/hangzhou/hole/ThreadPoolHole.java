package com.cn.hangzhou.hole;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 过独木桥问题
 * @author Administrator
 *
 */
public class ThreadPoolHole {
	
 		private static final int       LOOP_COUNT = 10;

	 	private static final int       THREAD_COUNT = 2;

	    private static ExecutorService threadPool   = Executors.newFixedThreadPool(THREAD_COUNT);
	     
	    public static void main(String[] args) {
	        for (int i = 0; i < LOOP_COUNT; i++) {
	            threadPool.execute(new Runnable() {
	                @Override
	                public void run() {
	                    try {
	                        System.out.println(Thread.currentThread().getName());
	                        TimeUnit.SECONDS.sleep(2);
	                    } catch (InterruptedException e) {	
	                    }   
	                }
	            });
	        }

	        System.out.println(1);
	        threadPool.shutdown();
	        System.out.println(2);

	    }
	
}
