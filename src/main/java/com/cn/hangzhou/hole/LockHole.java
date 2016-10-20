package com.cn.hangzhou.hole;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 过独木桥问题
 * @author Administrator
 *
 */
public class LockHole {
	
	 	private static final int       THREAD_COUNT = 10;

	    private static ExecutorService threadPool   = Executors.newFixedThreadPool(THREAD_COUNT);
	    
	    static Lock lock = new ReentrantLock();
	    
	    public static void main(String[] args) {
	        for (int i = 0; i < THREAD_COUNT; i++) {
	            threadPool.execute(new Runnable() {
	                @Override
	                public void run() {
	                	lock.lock();
	                    try {
	                        System.out.println(Thread.currentThread().getName());
	                        TimeUnit.SECONDS.sleep(5);
	                    } catch (InterruptedException e) {
	                    	
	                    }finally{
	                    	lock.unlock();
	                    }      
	                }
	            });
	        }

	        threadPool.shutdown();
	    }
	
}
