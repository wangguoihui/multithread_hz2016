package com.cn.hangzhou;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 生产者
 * @author Administrator
 *
 */
public class Producer implements Runnable {
	
	private final BlockingQueue<Integer> sharedQueue;
	 
    public Producer(BlockingQueue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
 
    @Override
    public void run() {
        for(int i=0; i < 100; i++){
            try {
                System.out.println(Thread.currentThread().getName() +", capacity: "+ sharedQueue.size());
                System.out.println("	"+Thread.currentThread().getName() +": "+ i);
            	sharedQueue.put(i);
            	Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
