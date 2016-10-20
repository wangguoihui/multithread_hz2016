package com.cn.hangzhou.hole;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 过独木桥问题
 * @author Administrator
 *
 */
public class ProducerConsumerHole {
	
	 	private LinkedList<Integer> products = new LinkedList<>();
	    private static final int MAX_SIZE = 2; //仓库容量
	 	private static final int THREAD_COUNT = 10;
	    private Lock lock = new ReentrantLock();
	    private Condition notEmpty = lock.newCondition();
	    private Condition notFull = lock.newCondition();
	    
	    public void produce(Integer product) {
	        lock.lock();
	        try {
	            while (products.size() == MAX_SIZE) {
	                notFull.await();
	            }
	            products.add(product);
                System.out.println(Thread.currentThread().getName()+"  生产了...size="+products.size());
                TimeUnit.SECONDS.sleep(1);
	            notEmpty.signal();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } finally {
	            lock.unlock();
	        }
	    }
	    public Integer consume() {
	        lock.lock();
	        Integer product = null;
	        try {
	            while (products.size() == 0) {
	                notEmpty.await();
	            }
	            product = products.removeLast();
                System.out.println(Thread.currentThread().getName()+"  消费了...size="+products.size());
                TimeUnit.SECONDS.sleep(1);
	            notFull.signal();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } finally {
	            lock.unlock();
	        }
	        return product;
	    }
	    
	    public static void main(String args[]){
	    	final ProducerConsumerHole producerConsumerHole = new ProducerConsumerHole();
	    	for (int i = 0; i < THREAD_COUNT; i++) {
	        	Thread producer = new Thread(new Runnable() {
					@Override
					public void run() {
						producerConsumerHole.produce(0);
					}
				});
	        	Thread consumer = new Thread(new Runnable() {
					@Override
					public void run() {
						producerConsumerHole.consume();
					}
				});
	        	
	        	producer.start();
	        	consumer.start();
	        }
	    }
	
}
