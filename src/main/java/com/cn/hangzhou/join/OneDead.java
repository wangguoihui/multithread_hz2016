package com.cn.hangzhou.join;
/**
 * 线程join自身发生死锁
 * 一个正在运行中的线程调用join()来等待自己结束
 * @author Administrator
 *
 */
public class OneDead {	 	
	    
	    public static void main(String[] args) {
        	new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread is starting...");  
                	try {
                		Thread.currentThread().join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                    System.out.println("Thread is stopping...");  
                }
            }).start();
	    }
	
}
