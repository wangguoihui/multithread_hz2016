package com.cn.hangzhou;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 基于有界阻塞队列的生产者消费者模式
 * @author Administrator
 *
 */
public class ProducerConsumerTest {
		
		public static void main(String args[]) {
			
			BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>(1);
		    new Thread(new Producer(sharedQueue),"Producer_A").start();
		    new Thread(new Consumer(sharedQueue),"Consumer_A").start();
		    new Thread(new Consumer(sharedQueue),"Consumer_B").start();

		}
}
