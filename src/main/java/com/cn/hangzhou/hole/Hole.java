package com.cn.hangzhou.hole;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 过独木桥问题
 * @author Administrator
 *
 */
public class Hole {
	
	 	private static final int       THREAD_COUNT = 10;
	 	
	 	private static final int       SHARE_COUNT = 2;
	 	
	    private static AtomicInteger atomicI = new AtomicInteger(0);
	 		 		    
	    static Lock lock = new ReentrantLock();
	    private static Condition notEmpty = lock.newCondition();
	    private static Condition notFull = lock.newCondition();
	    
	    public static void main(String[] args) {
	        for (int i = 0; i < THREAD_COUNT; i++) {
	        	Thread thread = new Thread(new Runnable() {
	                @Override
	                public void run() {
	                	lock.lock();
	                	while(atomicI.get() == SHARE_COUNT){
	                		try {
	                			notFull.await();
							} catch (InterruptedException e) {
							}
	                	}
	                    try {
	                    	atomicI.incrementAndGet();
	                        System.out.println(Thread.currentThread().getName());
	                        TimeUnit.SECONDS.sleep(2);
	                        atomicI.decrementAndGet();
	                        notEmpty.signalAll();
	                    } catch (InterruptedException e) {
	                    } finally{  
	                    	lock.unlock();
	                    }
	                }
	            });
	        	
	        	thread.start();
	        }
	    }
	
}
