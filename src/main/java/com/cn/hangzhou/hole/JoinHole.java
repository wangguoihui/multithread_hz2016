package com.cn.hangzhou.hole;

import java.util.concurrent.TimeUnit;

/**
 * 过独木桥问题
 * @author Administrator
 *
 */
public class JoinHole {
	
	 	private static final int       THREAD_COUNT = 10;
	    
	    public static void main(String[] args) {
	        for (int i = 0; i < THREAD_COUNT; i++) {
	        	Thread thread = new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    try {
	                        System.out.println(Thread.currentThread().getName());
	                        TimeUnit.SECONDS.sleep(5);
	                    } catch (InterruptedException e) {
	                    	
	                    }  
	                }
	            });
	        	
	        	thread.start();
	        	try {
					thread.join();
				} catch (InterruptedException e) {
				}
	        }
	    }
	
}
