package com.cn.hangzhou;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 消费者
 * @author Administrator
 *
 */
public class Consumer implements Runnable {
	
	private final BlockingQueue<Integer> sharedQueue;
	 
    public Consumer(BlockingQueue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
 
    @Override
    public void run() {
            try {
            	while(true){
            		System.out.println("	"+Thread.currentThread().getName()+": "+ sharedQueue.take());
            	}
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
}
