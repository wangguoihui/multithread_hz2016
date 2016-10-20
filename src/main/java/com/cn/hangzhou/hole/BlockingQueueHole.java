package com.cn.hangzhou.hole;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 过独木桥问题
 * @author Administrator
 *
 */
public class BlockingQueueHole {
	
	 	private static final int       THREAD_COUNT = 10;

	    private static ExecutorService threadPool   = Executors.newFixedThreadPool(THREAD_COUNT);

	    private static BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(2);
	    
	    public static void main(String[] args) {
	        for (int i = 0; i < THREAD_COUNT; i++) {
	            threadPool.execute(new Runnable() {
	                @Override
	                public void run() {
	                    try {
	                    	blockingQueue.put(1);
	                        System.out.println(Thread.currentThread().getName());
	                        TimeUnit.SECONDS.sleep(5);
	                    } catch (InterruptedException e) {
	                    	
	                    }finally{
	                    	try {
								blockingQueue.take();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
	                    }      
	                }
	            });
	        }

	        threadPool.shutdown();
	    }
	
}
